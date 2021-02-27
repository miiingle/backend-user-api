package net.miiingle.user.api.presentation.hateos;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Schema(
        name = "PagedCollection",
        description = "Similar to Collection, but includes the page metadata"
)
@Produces(MediaType.APPLICATION_JSON)
@Getter
@Introspected
public class PagedCollectionResource<T> extends CollectionResource<T> {

    private final PageMetadata page;

    private PagedCollectionResource(List<T> contents, PageMetadata page) {
        super(contents);
        this.page = page;
    }

    public static <T> PagedCollectionResource<T> create(List<T> contents, PageMetadata page) {
        return new PagedCollectionResource<>(contents, page);
    }
}
