package net.miiingle.user.api.business.core.data;

import io.micronaut.core.util.StringUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String id;
    private String name;
    private String email;
    private String password;

    public boolean validatesToCredentials(String email, String password) {
        return isPresent(email)
                && isPresent(password)
                && email.equals(this.email)
                && password.equals(this.password);
    }

    private boolean isPresent(String value) {
        return StringUtils.isNotEmpty(value);
    }
}
