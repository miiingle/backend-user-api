package net.miiingle.user.api.profile.impl;

import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.profile.core.ProfileDoesNotExist;
import net.miiingle.user.api.profile.core.ProfileService;
import net.miiingle.user.api.profile.core.UserProfile;

import javax.inject.Singleton;

@RequiredArgsConstructor
@Singleton
public class ProfileServiceImpl implements ProfileService {

    private final UserProfileRepository repository;

    @Override
    public UserProfile findById(String id) {
        return repository.findById(id)
                .orElseThrow(ProfileDoesNotExist::create);
    }

    @Override
    public void update(UserProfile userProfile) {
        if (repository.existsById(userProfile.getId())) {
            repository.update(userProfile);
        } else {
            repository.save(userProfile);
        }
    }
}
