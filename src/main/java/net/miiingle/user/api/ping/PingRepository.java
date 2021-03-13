package net.miiingle.user.api.ping;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

@Repository
@JdbcRepository
public interface PingRepository extends CrudRepository<PingEntity, Long> {}
