package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Status;
import io.micronaut.http.hateoas.Link;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.presentation.data.SimpleUserRepresentation;
import net.miiingle.user.api.presentation.data.hateos.CollectionResource;
import net.miiingle.user.api.presentation.data.hateos.PageMetadata;
import net.miiingle.user.api.presentation.data.hateos.PagedCollectionResource;

import java.util.LinkedList;
import java.util.List;

@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("users/{userId}/friends")
public class FriendshipQueryController {

    @Operation(
            operationId = "searchAll",
            summary = "List all the friends",
            description = "List the friends of the current user",
            tags = {"Friendship"}
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "List of all the users friends",
                    content = {
                            @Content(
                                    schema = @Schema(
                                            type = "object",
                                            name = "PagedCollection<SimpleUser>",
                                            description = "A list of basic user information",
                                            allOf = {
                                                    CollectionResource.SimpleUserRepresentationType.class,
                                                    PagedCollectionResource.class
                                            }
                                    )
                            )
                    }
            )
    )
    @Get
    @Status(HttpStatus.OK)
    public PagedCollectionResource<SimpleUserRepresentation> listAll(@PathVariable String userId) {

        List<SimpleUserRepresentation> fakeFriends = new LinkedList<>();
        fakeFriends.add(SimpleUserRepresentation.builder().build());
        fakeFriends.add(SimpleUserRepresentation.builder()
                .id("100001")
                .fullName("Test Test")
                .photoUrl("https://test.com/test.jpg")
                .build());

        PageMetadata metadata = PageMetadata.builder()
                .number(0).size(25).totalItems(1000L).totalPages(40)
                .build();

        var result = PagedCollectionResource.create(fakeFriends, metadata);
        result.link(Link.SELF, "https://something/something");

        return result;
    }

}
