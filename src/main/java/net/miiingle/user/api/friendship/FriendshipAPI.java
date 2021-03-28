package net.miiingle.user.api.friendship;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Friendship")
@RequiredArgsConstructor
@Slf4j
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/friendship")
public class FriendshipAPI {

    @Get
    public HttpResponse<FriendshipStatus> checkFriendshipStatus(String userId) {
        if (userId.contains("x")) {
            return HttpResponse.created(FriendshipStatus.accepted(userId));
        } else {
            return HttpResponse.ok(FriendshipStatus.rejected(userId));
        }
    }

    @Put("request")
    public HttpResponse<FriendshipStatus> requestFriendship(FriendRequest friendRequest) {
        log.info("Request: {}", friendRequest);
        if (friendRequest.getUserId().contains("x")) {
            return HttpResponse.created(FriendshipStatus.accepted(friendRequest.userId));
        } else {
            return HttpResponse.ok(FriendshipStatus.pending(friendRequest.userId));
        }
    }

    @Put("respond")
    public void respondToFriendship(FriendResponse friendResponse) {
        log.info("Response: {}", friendResponse);

    }

}
