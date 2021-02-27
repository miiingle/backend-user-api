package net.miiingle.user.api.presentation.data.hateos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema()
public class ResourceError {

    String code;
    String description;
}
