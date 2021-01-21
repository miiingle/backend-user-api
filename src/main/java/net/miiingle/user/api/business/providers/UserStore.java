package net.miiingle.user.api.business.providers;

import net.miiingle.user.api.business.core.data.User;

import java.util.Optional;

public interface UserStore {

    Optional<User> findByEmail(String email);
}
