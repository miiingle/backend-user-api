package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.presentation.data.SimpleUserRepresentation;
import net.miiingle.user.api.presentation.data.shared.PageMetadata;
import net.miiingle.user.api.presentation.data.shared.ResourceCollection;

import java.util.LinkedList;
import java.util.List;

@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("users/{userId}/friends/search")
public class FriendshipQueryController {

    @Operation(
            operationId = "searchAll",
            summary = "List all the friends",
            description = "List the friends of the current user",
            tags = {"Friendship"}
    )
    @Get
    @Status(HttpStatus.OK)
    public ResourceCollection<SimpleUserRepresentation, PageMetadata> listAll(@PathVariable String userId) {

        List<SimpleUserRepresentation> fakeFriends = new LinkedList<>();
        PageMetadata metadata = new PageMetadata();

        return ResourceCollection.createNew(fakeFriends, metadata);
    }

}
