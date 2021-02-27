package net.miiingle.user.api.presentation.hateos;

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

    @Schema(example = "0")
    private final Integer number;

    @Schema(example = "25")
    private final Integer size;

    @Schema(example = "100")
    private final Integer totalPages;

    @Schema(example = "2500")
    private final Long totalItems;
}
