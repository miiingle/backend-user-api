package net.miiingle.user.api.presentation;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import net.miiingle.user.api.business.UserRegistry;
import net.miiingle.user.api.business.data.Registration;

import javax.inject.Inject;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class RegistrationController {

    @Inject
    UserRegistry userRegistry;

    @Post("/register")
    public void registerReactor(@Body Registration registration) {
        userRegistry.register(registration);
    }
}
