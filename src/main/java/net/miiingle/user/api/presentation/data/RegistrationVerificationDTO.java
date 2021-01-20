package net.miiingle.user.api.presentation.data;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import lombok.Data;
import net.miiingle.user.api.business.data.RegistrationVerification;

import javax.annotation.Nullable;

@Data
@Introspected
public class RegistrationVerificationDTO {

    @PathVariable
    String registrationId;

    @Nullable
    @QueryValue
    String code;

    public RegistrationVerification convert() {
        return RegistrationVerification.builder()
                .registrationId(registrationId)
                .code(code)
                .build();
    }
}
