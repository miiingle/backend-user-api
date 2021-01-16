package net.miiingle.user.api.business;


import net.miiingle.user.api.persistence.RegistrationEntity;
import net.miiingle.user.api.persistence.RegistrationRepository;
import net.miiingle.user.api.externalservice.EmailSender;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RegistrationManager {

    @Inject
    RegistrationRepository repository;

    @Inject
    EmailSender emailSender;

    /**
     * initiates the registration process of an anonymous user
     * a registration does not automatically create an account
     * you need to verify a registration
     *
     * @param registration
     */
    public void register(Registration registration) {
        emailSender.send(EmailSender.MessageRequest.builder().build());
        repository.save(RegistrationEntity.builder().email(registration.getEmail()).build());
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
