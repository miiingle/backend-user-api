package net.miiingle.user.api.business.core;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.miiingle.user.api.business.core.data.RegistrationRequest;
import net.miiingle.user.api.business.core.data.RegistrationVerification;
import net.miiingle.user.api.business.core.data.User;
import net.miiingle.user.api.business.core.exception.FailedToSendEmail;
import net.miiingle.user.api.business.core.exception.InvalidVerification;
import net.miiingle.user.api.business.providers.EmailSender;
import net.miiingle.user.api.business.providers.UserStore;
import net.miiingle.user.api.client.persistence.repository.RegistrationRepository;
import net.miiingle.user.api.client.persistence.repository.UserCredentialRepository;
import net.miiingle.user.api.client.persistence.repository.UserProfileRepository;
import net.miiingle.user.api.client.persistence.data.Registration;
import net.miiingle.user.api.client.persistence.data.UserCredential;
import net.miiingle.user.api.client.persistence.data.UserProfile;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Singleton
@Transactional
public class UserRegistry {

    private final RegistrationRepository registrationRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final UserProfileRepository userProfileRepository;
    private final EmailSender emailSender;
    private final UserStore userStore;

    /**
     * initiates the registration process of an anonymous user
     * a registration does not automatically create an account
     * you need to verify a registration
     *
     * @param registrationRequest a request to start a membership
     */
    public Long register(RegistrationRequest registrationRequest) {
        String confirmationCode = "000000";

        var createdRegistration = storeRegistration(registrationRequest, confirmationCode);

        tryToSendConfirmationCodeFor(createdRegistration, confirmationCode);

        return createdRegistration.getId();
    }

    /**
     * when the verification matches a pending registration a new account is created
     * a successful verification should allow the user to login
     *
     * a verification may fail given the following scenarios:
     * - it was already verified
     * - incorrect verification code
     *
     * @param verification a request to confirm a registration and create a new account
     */
    public void verify(RegistrationVerification verification) {

        var registration = findValidRegistrationOrError(verification);

        confirm(registration);

        var userID = generateUserId();
        var userPassword = generatePassword();
        var email = registration.getEmail();

        createNewProfile(registration, userID);
        createCredentials(userID, userPassword, email);

        sendPasswordToUserEmail(email, String.format("Hello %s, Your temporary password is %s", registration.getName(), userPassword));
    }

    /**
     * returns the user uniquely identified by the email
     *
     * @param email the unique identifier / login username
     *
     * @return a user if it is present
     */
    public Optional<User> fetchUserWith(String email) {
        return userStore.findByEmail(email);
    }

    private void tryToSendConfirmationCodeFor(Registration registration, String confirmationCode) {
        try {
            emailSender.send(EmailSender.MessageRequest.builder()
                    .emailAddress(registration.getEmail())
                    .message("Registration " + registration.getId() + " | Confirmation Code: " + confirmationCode)
                    .build());
        } catch (Exception e) {
            throw new FailedToSendEmail();
        }
    }

    private Registration storeRegistration(RegistrationRequest registrationRequest, String confirmationCode) {
        return registrationRepository.save(Registration.builder()
                .email(registrationRequest.getEmail())
                .name(registrationRequest.getName())
                .confirmationCode(confirmationCode)
                .confirmed(false)
                .build());
    }

    private void sendPasswordToUserEmail(String email, String format) {
        emailSender.send(EmailSender.MessageRequest.builder()
                .emailAddress(email)
                .message(format)
                .build());
    }

    private void createCredentials(String userID, String userPassword, String email) {
        userCredentialRepository.save(UserCredential.builder()
                .username(email)
                .password(userPassword)
                .internalId(userID)
                .build());
    }

    private void createNewProfile(Registration registration, String userID) {
        userProfileRepository.save(UserProfile.builder()
                .id(userID)
                .email(registration.getEmail())
                .name(registration.getName())
                .build());
    }

    private void confirm(Registration registration) {
        registration.setConfirmed(true);
        registrationRepository.save(registration);
    }

    private Registration findValidRegistrationOrError(RegistrationVerification verification) {
        Optional<Registration> maybeRegistration = registrationRepository.findById(Long.parseLong(verification.getRegistrationId()));

        if (maybeRegistration.isEmpty()) {
            throw new InvalidVerification();
        }

        var registration = maybeRegistration.get();

        if (verification.isInvalidFor(registration)) {
            throw new InvalidVerification();
        }

        return registration;
    }

    private String generateUserId() {
        return UUID.randomUUID().toString();
    }

    private String generatePassword() {
        return "test1234";
    }

}
