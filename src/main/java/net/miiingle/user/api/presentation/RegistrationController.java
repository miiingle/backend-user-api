package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.core.UserRegistry;
import net.miiingle.user.api.business.core.data.RegistrationRequest;
import net.miiingle.user.api.presentation.data.shared.NewResource;
import net.miiingle.user.api.presentation.data.RegistrationVerificationDTO;

@RequiredArgsConstructor
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/registrations")
public class RegistrationController {

    private final UserRegistry userRegistry;

    @Operation(
            operationId = "registrationStart",
            summary = "Initiate the Registration process",
            description = "Starts the process of a user registration. At this point no user account is created, yet",
            tags = {"Registration"}
    )
    @Post("/")
    @Status(HttpStatus.CREATED)
    public NewResource startRegistration(@Body RegistrationRequest registrationRequest) {
        return NewResource.create(userRegistry.register(registrationRequest).toString());
    }

    @Operation(
            operationId = "registrationVerifyLink",
            summary = "Complete the registration process using HTTP link",
            description = "Verifies the provided user details and creates the user profile and login",
            tags = {"Registration"}
    )
    @Get("/{registrationId}/verify{?code}")
    @Status(HttpStatus.OK)
    public void verifyRegistration(@RequestBean RegistrationVerificationDTO verification) {
        userRegistry.verify(verification.convert());
    }

}
