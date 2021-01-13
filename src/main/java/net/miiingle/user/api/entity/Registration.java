package net.miiingle.user.api.entity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

@Data
@MappedEntity
@Introspected
public class Registration {

    @Id
    @GeneratedValue
    Long id;

    String email;
    String confirmationCode;
    Boolean confirmed;
}
