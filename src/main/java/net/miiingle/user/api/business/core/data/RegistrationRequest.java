package net.miiingle.user.api.business.core.data;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(name = "Registration")
@Getter
@Builder
@Introspected
public class RegistrationRequest {

    @Schema(example = "john.doe@business.com")
    private final String email;

    @Schema(example = "John Doe")
    private final String name;
}
