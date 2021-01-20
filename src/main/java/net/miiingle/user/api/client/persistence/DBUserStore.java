package net.miiingle.user.api.client.persistence;

import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.data.User;
import net.miiingle.user.api.business.service.UserStore;
import net.miiingle.user.api.client.persistence.data.UserCredential;
import net.miiingle.user.api.client.persistence.data.UserProfile;
import net.miiingle.user.api.client.persistence.repository.UserCredentialRepository;
import net.miiingle.user.api.client.persistence.repository.UserProfileRepository;

import javax.inject.Singleton;
import java.util.Optional;

@RequiredArgsConstructor
@Singleton
public class DBUserStore implements UserStore {

    private final UserCredentialRepository userCredentialRepository;
    private final UserProfileRepository userProfileRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserCredential> maybeCredential = userCredentialRepository.findByUsername(email);

        if (maybeCredential.isEmpty()) {
            return Optional.empty();
        }

        UserCredential credential = maybeCredential.get();

        Optional<UserProfile> maybeProfile = userProfileRepository.findById(credential.getInternalId());

        if (maybeProfile.isEmpty()) {
            return Optional.empty();
        }

        UserProfile profile = maybeProfile.get();

        User user = User.builder()
                .id(credential.getInternalId())
                .email(credential.getUsername())
                .password(credential.getPassword())
                .name(profile.getName())
                .build();

        return Optional.of(user);
    }
}
