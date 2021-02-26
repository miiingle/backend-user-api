package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.core.UserRegistry;
import net.miiingle.user.api.business.core.data.User;

@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users")
public class UserQueryController {

    private final UserRegistry userRegistry;

    @Operation(
            operationId = "userMyProfile",
            summary = "My Profile",
            description = "Shows the profile of the current user",
            tags = {"User"}
    )
    @Get("/me")
    @Status(HttpStatus.OK)
    public User showMyProfile(Authentication authentication) {
        return userRegistry.fetchUserWith(authentication.getName()).orElseThrow();
    }

    @Operation(
            operationId = "userProfile",
            summary = "My Profile",
            description = "Shows the profile of the user with the id",
            tags = {"User"}
    )
    @Get("/{id}")
    @Status(HttpStatus.OK)
    public User showProfileById(@PathVariable String id) {
        return null;
    }
}
