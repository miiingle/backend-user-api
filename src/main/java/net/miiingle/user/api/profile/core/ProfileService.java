package net.miiingle.user.api.profile.core;

import java.util.Optional;

public interface ProfileService {

    Optional<UserProfile> findById(String id);

    void update(UserProfile userProfile);

}
