package net.miiingle.user.api.client.persistence.data;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
@Introspected
public class RegistrationEntity {

    @Id
    @GeneratedValue
    Long id;

    String email;
    String confirmationCode;
    Boolean confirmed;
}
