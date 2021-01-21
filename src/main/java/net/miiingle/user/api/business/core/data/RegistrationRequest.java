package net.miiingle.user.api.business.core.data;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class RegistrationRequest {

    String email;
    String name;
}
