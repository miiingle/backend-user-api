package net.miiingle.user.api.profile.impl;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;
import net.miiingle.user.api.profile.core.UserProfile;

@Repository
@JdbcRepository
public interface UserProfileRepository extends CrudRepository<UserProfile, String> {
}
