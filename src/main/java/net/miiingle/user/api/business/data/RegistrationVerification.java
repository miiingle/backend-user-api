package net.miiingle.user.api.business.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.miiingle.user.api.client.persistence.data.Registration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class RegistrationVerification {

    String registrationId;
    String code;

    @JsonIgnore
    public boolean isInvalidFor(Registration registration) {
        return !isValidFor(registration);
    }

    private boolean isValidFor(Registration registration) {
        return registration != null
                && !registration.getConfirmed()
                && StringUtils.isNotEmpty(code)
                && StringUtils.isNotEmpty(registration.getConfirmationCode())
                && code.equals(registration.getConfirmationCode());
    }
}
