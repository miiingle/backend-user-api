package net.miiingle.user.api.presentation.data.shared;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.List;

@Data
@Introspected
public class ResourceCollection<T, M> {

    public List<T> contents;
    public M metadata;

    private ResourceCollection(List<T> contents, M metadata) {
        this.contents = contents;
        this.metadata = metadata;
    }

    public static <T, M> ResourceCollection<T,M> createNew(List<T> contents, M metadata) {
        return new ResourceCollection<>(contents, metadata);
    }
}
