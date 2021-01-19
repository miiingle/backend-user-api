package net.miiingle.user.api.client.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.miiingle.user.api.client.persistence.data.UserCredential;

@Repository
public interface UserCredentialRepository extends PageableRepository<UserCredential, Long> {
}
