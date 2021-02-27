package net.miiingle.user.api.presentation.data;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Introspected
public class SimpleUserRepresentation {

    String id;
    String fullName;
    String photoUrl;
}
