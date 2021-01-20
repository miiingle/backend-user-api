package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.UserRegistry;

@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users")
public class UserController {

    private final UserRegistry userRegistry;

    @Operation(
            operationId = "showMyProfile",
            summary = "My Profile",
            description = "Shows the profile of the current user",
            tags = {"User"}
    )
    @Get("/me")
    @Status(HttpStatus.OK)
    public String showMyProfile() {
        return "test";
    }
}
