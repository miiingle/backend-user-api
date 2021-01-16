package net.miiingle.user.api.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

@Repository
public interface RegistrationRepository extends PageableRepository<RegistrationEntity, Long> {
}
