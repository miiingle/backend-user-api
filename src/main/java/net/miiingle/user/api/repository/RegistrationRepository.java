package net.miiingle.user.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.miiingle.user.api.entity.Registration;

@Repository
public interface RegistrationRepository extends PageableRepository<Registration, Long> {
}
