package net.miiingle.user.api.profile.core;

public interface ProfileService {

    UserProfile findById(String id);

    void update(UserProfile userProfile);

}
