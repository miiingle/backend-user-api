package net.miiingle.user.api.presentation.data.hateos;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.AbstractResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(
        name = "Identifier",
        description = "A newly-created resource that only has its id for the client to keep track"
)
@Produces(MediaType.APPLICATION_JSON)
@Getter
@Introspected
public class IdentifierResource extends AbstractResource<IdentifierResource> {

    private final String id;

    private IdentifierResource(String id) {
        this.id = id;
    }

    public static IdentifierResource create(String id) {
        return new IdentifierResource(id);
    }
}
