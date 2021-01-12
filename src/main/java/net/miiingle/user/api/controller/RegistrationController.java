package net.miiingle.user.api.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.rxjava3.core.Single;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/")
public class RegistrationController {

    @Get("/ping")
    public Single<String> ping() {
        return Single.just("test");
    }
}
