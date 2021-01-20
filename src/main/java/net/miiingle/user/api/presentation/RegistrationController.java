package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.UserRegistry;
import net.miiingle.user.api.business.data.RegistrationRequest;
import net.miiingle.user.api.presentation.data.RegistrationVerificationDTO;

@RequiredArgsConstructor
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class RegistrationController {

    private final UserRegistry userRegistry;

    @Operation(
            operationId = "registrationStart",
            summary = "Initiate the Registration process",
            description = "Starts the process of a user registration. At this point no user account is created, yet",
            tags = {"Registration"}
    )
    @Post("/registration")
    @Status(HttpStatus.CREATED)
    public void startRegistration(@Body RegistrationRequest registrationRequest) {
        userRegistry.register(registrationRequest);
    }

    @Operation(
            operationId = "registrationVerification",
            summary = "Initiate the Registration process",
            description = "Starts the process of a user registration. At this point no user account is created, yet",
            tags = {"Registration"}
    )
    @Get("/registration/{registrationId}/verification{?code}")
    @Status(HttpStatus.OK)
    public void verifyRegistration(@RequestBean RegistrationVerificationDTO verification) {
        userRegistry.verify(verification.convert());
    }
}
