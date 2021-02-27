package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.presentation.data.RegistrationStatus;

@RequiredArgsConstructor
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/registrations")
public class RegistrationQueryController {

    @Operation(
            operationId = "registrationVerifyLink",
            summary = "Complete the registration process using HTTP link",
            description = "Verifies the provided user details and creates the user profile and login",
            tags = {"Registration"}
    )
    @Get("/{registrationId}/status")
    @Status(HttpStatus.OK)
    public RegistrationStatus checkStatus(@PathVariable String registrationId) {
        return RegistrationStatus.builder()
                .status("PENDING")
                .message("Awaiting verification")
                .build();
    }
}
