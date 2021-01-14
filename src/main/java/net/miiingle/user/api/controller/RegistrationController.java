package net.miiingle.user.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import net.miiingle.user.api.entity.Registration;
import net.miiingle.user.api.repository.RegistrationRepository;

import javax.inject.Inject;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/")
public class RegistrationController {

    @Inject
    RegistrationRepository repository;

    @Post("/register")
    public Registration registerReactor(@Body Registration registration) {
        return repository.save(registration);
    }
}
