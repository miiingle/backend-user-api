package net.miiingle.user.api.repository;

import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import net.miiingle.user.api.entity.Registration;

@R2dbcRepository
public interface RegistrationRepository extends ReactiveStreamsCrudRepository<Registration, Long> {
}
