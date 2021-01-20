package net.miiingle.user.api.integration.security;

import io.micronaut.security.authentication.UserDetails;
import net.miiingle.user.api.business.data.User;

import java.util.Collections;

public class AppUserDetails extends UserDetails {

    private final User user;

    private AppUserDetails(User user) {
        super(user.getEmail(), Collections.emptyList());
        this.user = user;
    }

    public static AppUserDetails from(User user) {
        return new AppUserDetails(user);
    }
}
