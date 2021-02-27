package net.miiingle.user.api.presentation.data;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(
        name = "SimpleUser",
        description = "A basic representation of a single user"
)
@Data
@Builder
@Introspected
public class SimpleUserRepresentation {

    @Schema(
            example = "100001"
    )
    String id;

    @Schema(
            example = "John Doe"
    )
    String fullName;

    @Schema(
            example = "https://s3.aws.com/100001/profile.jpg"
    )
    String photoUrl;
}
