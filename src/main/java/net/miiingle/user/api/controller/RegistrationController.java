package net.miiingle.user.api.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.rxjava3.core.Single;
import net.miiingle.user.api.entity.Registration;
import net.miiingle.user.api.repository.RegistrationRepository;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/")
public class RegistrationController {

    @Inject
    RegistrationRepository repository;

    @Post("/registerrx")
    @Transactional
    public Single<Registration> registerRxJava(@Body Registration registration) {
        return Single.fromPublisher(repository.save(registration));
    }

    @Post("/register")
    @Transactional
    public Publisher<Registration> registerReactor(@Body Registration registration) {
        return repository.save(registration);
    }
}
