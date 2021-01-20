package net.miiingle.user.api.client.persistence.repository;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.miiingle.user.api.client.persistence.data.UserCredential;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserCredentialRepository extends PageableRepository<UserCredential, Long> {

    @NonNull
    Optional<UserCredential> findByUsername(@NonNull @NotNull String username);
}
