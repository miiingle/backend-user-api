package net.miiingle.user.api.presentation.data;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.hateoas.AbstractResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema
@Getter
@Builder
@Introspected
public class RegistrationStatus extends AbstractResource<RegistrationStatus> {

    @Schema(
            allowableValues = {
                    "PENDING",
                    "VERIFIED",
                    "REJECTED"
            }
    )
    private final String status;

    @Schema(example = "Registration is awaiting verification")
    private final String message;

}
