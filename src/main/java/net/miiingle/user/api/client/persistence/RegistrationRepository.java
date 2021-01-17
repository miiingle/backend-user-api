package net.miiingle.user.api.client.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.miiingle.user.api.client.persistence.data.RegistrationEntity;

@Repository
public interface RegistrationRepository extends PageableRepository<RegistrationEntity, Long> {
}
