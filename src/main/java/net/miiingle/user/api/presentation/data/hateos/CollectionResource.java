package net.miiingle.user.api.presentation.data.hateos;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.AbstractResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import net.miiingle.user.api.presentation.data.SimpleUserRepresentation;

import java.util.List;

@Schema(
        name = "Collection",
        description = "A resource that has a collection of other resource"
)
@Produces(MediaType.APPLICATION_JSON)
@Getter
@Introspected
public class CollectionResource<T> extends AbstractResource<CollectionResource<T>> {

    private final List<T> contents;

    protected CollectionResource(List<T> contents) {
        this.contents = contents;
    }

    public static <T> CollectionResource<T> create(List<T> contents) {
        return new CollectionResource<>(contents);
    }

    @Schema
    public interface SimpleUserRepresentationType {
        List<SimpleUserRepresentation> getContents();
    }
}
