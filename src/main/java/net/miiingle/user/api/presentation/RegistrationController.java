package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.UserRegistry;
import net.miiingle.user.api.business.data.Registration;

@RequiredArgsConstructor
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class RegistrationController {

    private final UserRegistry userRegistry;

    @Operation(
            summary = "Initiate the Registration process",
            description = "Starts the process of a user registration. At this point no user account is created, yet",
            tags = {"registration"}
    )
    @Post("/register")
    @Status(HttpStatus.CREATED)
    public void registrationCreate(@Body Registration registration) {
        userRegistry.register(registration);
    }
}
