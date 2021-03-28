package net.miiingle.user.api.friendship;

import lombok.Data;

@Data
public class FriendResponse {

    String userId;
    Response response;

    public enum Response {
        ACCEPT,
        REJECT
    }
}
