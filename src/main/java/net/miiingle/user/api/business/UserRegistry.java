package net.miiingle.user.api.business;


import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.data.Registration;
import net.miiingle.user.api.business.data.RegistrationVerification;
import net.miiingle.user.api.business.exception.FailedToSendEmail;
import net.miiingle.user.api.client.persistence.data.RegistrationEntity;
import net.miiingle.user.api.client.persistence.RegistrationRepository;
import net.miiingle.user.api.client.email.EmailSender;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
@Transactional
@RequiredArgsConstructor
public class UserRegistry {

    private final RegistrationRepository repository;
    private final EmailSender emailSender;

    /**
     * initiates the registration process of an anonymous user
     * a registration does not automatically create an account
     * you need to verify a registration
     *
     * @param registration
     */
    public void register(Registration registration) {
        repository.save(RegistrationEntity.builder()
                .email(registration.getEmail())
                .confirmationCode("0000")
                .confirmed(false)
                .build());

        tryToSendConfirmationCodeFor(registration);
    }

    private void tryToSendConfirmationCodeFor(Registration registration) {
        try {
            emailSender.send(EmailSender.MessageRequest.builder()
                    .emailAddress(registration.getEmail())
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
    public void verifyRegistration(RegistrationVerification verification) {

    }


}
