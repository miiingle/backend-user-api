package net.miiingle.user.api.ping;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@MappedEntity
public class PingEntity {

    @Id
    @GeneratedValue
    Long id;

    String name;

    LocalDateTime dateCreated;
}
