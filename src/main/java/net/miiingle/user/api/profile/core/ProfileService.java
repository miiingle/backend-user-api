package net.miiingle.user.api.profile.core;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

public interface ProfileService {

    UserProfile findById(String id);

    Page<UserProfile> searchByName(String name, Pageable page) ;

    void update(UserProfile userProfile);

}
