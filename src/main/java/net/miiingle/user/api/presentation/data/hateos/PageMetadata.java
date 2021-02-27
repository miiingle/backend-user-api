package net.miiingle.user.api.presentation.data.hateos;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(
        name = "Page",
        description = "Metadata to help display the values for regular pagination"
)
@Getter
@Builder
@Introspected
public class PageMetadata {

    private final Integer number;
    private final Integer size;
    private final Integer totalPages;
    private final Long totalItems;
}
