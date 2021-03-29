package net.miiingle.user.api.profile.core;

import io.micronaut.data.model.Page;

public interface ProfileService {

    UserProfile findById(String id);

    Page<UserProfile> searchByName(String name);

    void update(UserProfile userProfile);

}
