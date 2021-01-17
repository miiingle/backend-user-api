package net.miiingle.user.api.client.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.miiingle.user.api.client.persistence.data.RegistrationEntity;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends PageableRepository<RegistrationEntity, Long> {

    Optional<RegistrationEntity> findById(Long id);
}
