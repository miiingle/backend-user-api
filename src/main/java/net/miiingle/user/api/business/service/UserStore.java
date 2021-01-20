package net.miiingle.user.api.business.service;

import net.miiingle.user.api.business.data.User;

import java.util.Optional;

public interface UserStore {

    Optional<User> findByEmail(String email);
}
