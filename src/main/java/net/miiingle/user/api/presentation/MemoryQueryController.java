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
import net.miiingle.user.api.presentation.data.Memory;
import net.miiingle.user.api.presentation.data.hateos.PageMetadata;
import net.miiingle.user.api.presentation.data.hateos.PagedCollectionResource;

import java.util.LinkedList;
import java.util.List;

@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("users/{userId}/memories")
public class MemoryQueryController {

    @Operation(
            operationId = "user.memories.all",
            summary = "List all the memories",
            description = "List the friends of the current user",
            tags = {"Memory"}
    )
    @Get
    @Status(HttpStatus.OK)
    public PagedCollectionResource<Memory> listAll(@PathVariable String userId) {

        List<Memory> fakeMemories = new LinkedList<>();
        PageMetadata metadata = PageMetadata.builder().build();

        return PagedCollectionResource.create(fakeMemories, metadata);
    }
}
