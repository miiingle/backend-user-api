package net.miiingle.user.api.business.data;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class RegistrationVerification {

    String registrationId;
    String code;

}
