package net.miiingle.user.api.entity;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Introspected
public class Registration {

    @Id
    @GeneratedValue
    Long id;

    String email;
    String confirmationCode;
    Boolean confirmed;
}
