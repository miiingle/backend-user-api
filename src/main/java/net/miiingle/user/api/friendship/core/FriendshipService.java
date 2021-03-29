package net.miiingle.user.api.friendship.core;

import io.micronaut.data.model.Page;

public interface FriendshipService {

    void requestFriendship(String userId, String friendUserId);

    void acceptFriendship(String userId, String friendUserId);

    void rejectFriendship(String userId, String friendUserId);

}
