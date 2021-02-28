package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.core.UserRegistry;
import net.miiingle.user.api.business.core.data.RegistrationRequest;
import net.miiingle.user.api.presentation.hateos.IdentifierResource;
import net.miiingle.user.api.presentation.data.RegistrationVerificationDTO;
import net.miiingle.user.api.presentation.hateos.support.RegistrationResourceBuilder;

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
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "A registration was accepted, but will still need verification",
                    links = {
                            @Link(
                                    name = "VerifyRegistration",
                                    operationId = "registrationVerifyLink",
                                    parameters = @LinkParameter(
                                            name = "registrationId",
                                            expression = "$response.body#/id"
                                    ),
                                    description = "The `id` value returned in the response can be used as the path for verifying a registration"
                            )
                    }
            )
    )
    @Post
    @Status(HttpStatus.CREATED)
    public IdentifierResource startRegistration(@Body RegistrationRequest registrationRequest) {
        return RegistrationResourceBuilder.build(userRegistry.register(registrationRequest));
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
