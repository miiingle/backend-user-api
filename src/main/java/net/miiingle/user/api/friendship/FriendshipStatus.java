package net.miiingle.user.api.friendship;

import lombok.Value;

@Value
public class FriendshipStatus {

    String userId;
    Status status;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public static FriendshipStatus pending(String userId) {
        return new FriendshipStatus(userId, Status.PENDING);
    }

    public static FriendshipStatus accepted(String userId) {
        return new FriendshipStatus(userId, Status.ACCEPTED);
    }

    public static FriendshipStatus rejected(String userId) {
        return new FriendshipStatus(userId, Status.REJECTED);
    }


}
