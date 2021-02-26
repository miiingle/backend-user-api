package net.miiingle.user.api.presentation.data;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class FriendRequest {

    String friendId;
}
