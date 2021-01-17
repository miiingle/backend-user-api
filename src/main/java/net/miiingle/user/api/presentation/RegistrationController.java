package net.miiingle.user.api.presentation;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.UserRegistry;
import net.miiingle.user.api.business.data.Registration;

@RequiredArgsConstructor
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class RegistrationController {

    private final UserRegistry userRegistry;

    @Post("/register")
    public void registerReactor(@Body Registration registration) {
        userRegistry.register(registration);
    }
}
