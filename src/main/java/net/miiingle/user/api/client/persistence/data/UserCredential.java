package net.miiingle.user.api.client.persistence.data;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Introspected
public class UserCredential {

    @Id
    @GeneratedValue
    Long id;

    String username;
    String password;
    
    String internalId;
}
