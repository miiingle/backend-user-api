package net.miiingle.user.api.business;


import net.miiingle.user.api.persistence.RegistrationEntity;
import net.miiingle.user.api.persistence.RegistrationRepository;
import net.miiingle.user.api.service.EmailService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RegistrationManager {

    @Inject
    RegistrationRepository repository;

    @Inject
    EmailService emailService;

    public void register(Registration registration) {
        emailService.send(EmailService.MessageRequest.builder().build());
        repository.save(RegistrationEntity.builder().email(registration.getEmail()).build());
    }
}
