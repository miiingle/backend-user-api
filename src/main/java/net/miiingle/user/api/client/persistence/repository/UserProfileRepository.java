package net.miiingle.user.api.client.persistence.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.miiingle.user.api.client.persistence.data.UserProfile;

@Repository
public interface UserProfileRepository extends PageableRepository<UserProfile, String> {
}
