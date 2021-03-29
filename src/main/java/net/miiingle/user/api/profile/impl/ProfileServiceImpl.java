package net.miiingle.user.api.profile.impl;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
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
    public Page<UserProfile> searchByName(String name, Pageable page) {
        return repository.findAll(page);
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
