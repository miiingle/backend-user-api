package net.miiingle.user.api.presentation.data.shared;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class NewResource {

    String id;

    private NewResource(String id) {
        this.id = id;
    }

    public static NewResource create(String id) {
        return new NewResource(id);
    }
}
