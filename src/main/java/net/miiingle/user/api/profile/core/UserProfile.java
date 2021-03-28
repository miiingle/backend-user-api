package net.miiingle.user.api.profile.core;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@MappedEntity
@Data @AllArgsConstructor
public class UserProfile {

    @Id
    String id;

    String fullName;
}
