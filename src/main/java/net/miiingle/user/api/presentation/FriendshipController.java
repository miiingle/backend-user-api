package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.presentation.data.hateos.IdentifierResource;
import net.miiingle.user.api.presentation.data.FriendRequest;

@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("users/{userId}/friends")
public class FriendshipController {

    @Operation(
            operationId = "requestFriendship",
            summary = "Add a friend",
            description = "Request another user to be a friend",
            tags = {"Friendship"}
    )
    @Post
    @Status(HttpStatus.CREATED)
    public IdentifierResource requestFriendship(
            @PathVariable String userId,
            @Body FriendRequest friendRequest) {
        return IdentifierResource.create("10000");
    }
}
