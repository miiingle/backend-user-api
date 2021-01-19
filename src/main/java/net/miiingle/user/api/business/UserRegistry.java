package net.miiingle.user.api.business;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.miiingle.user.api.business.data.RegistrationRequest;
import net.miiingle.user.api.business.data.RegistrationVerification;
import net.miiingle.user.api.business.exception.FailedToSendEmail;
import net.miiingle.user.api.business.exception.InvalidVerification;
import net.miiingle.user.api.client.email.EmailSender;
import net.miiingle.user.api.client.persistence.RegistrationRepository;
import net.miiingle.user.api.client.persistence.data.Registration;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Singleton
@Transactional
public class UserRegistry {

    private final RegistrationRepository repository;
    private final EmailSender emailSender;

    /**
     * initiates the registration process of an anonymous user
     * a registration does not automatically create an account
     * you need to verify a registration
     *
     * @param registrationRequest
     */
    public void register(RegistrationRequest registrationRequest) {
        repository.save(Registration.builder()
                .email(registrationRequest.getEmail())
                .confirmationCode("0000")
                .confirmed(false)
                .build());

        tryToSendConfirmationCodeFor(registrationRequest);
    }

    private void tryToSendConfirmationCodeFor(RegistrationRequest registrationRequest) {
        try {
            emailSender.send(EmailSender.MessageRequest.builder()
                    .emailAddress(registrationRequest.getEmail())
                    .message("Confirmation Code: 000000")
                    .build());
        } catch (Exception e) {
            throw new FailedToSendEmail();
        }
    }

    /**
     * when the verification matches a pending registration a new account is created
     * a successful verification should allow the user to login
     *
     * a verification may fail given the following scenarios:
     * - it was already verified
     * - incorrect verification code
     *
     * @param verification
     */
    public void verify(RegistrationVerification verification) {

        var registration = findValidRegistrationOrError(verification);

        if (verification.isInvalidFor(registration)) {
            throw new InvalidVerification();
        }

        confirm(registration);

        log.info("Registration: {}", verification.getRegistrationId());
    }

    private void confirm(Registration registration) {
        registration.setConfirmed(true);
        repository.save(registration);
    }

    private Registration findValidRegistrationOrError(RegistrationVerification verification) {
        Optional<Registration> maybeRegistration = repository.findById(Long.parseLong(verification.getRegistrationId()));

        if (maybeRegistration.isEmpty()) {
            throw new InvalidVerification();
        }

        return maybeRegistration.get();
    }

}
