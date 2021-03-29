package net.miiingle.user.api.friendship;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

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

    @Get("list")
    public Page<FriendshipStatus> searchAllRequests() {

        List<FriendshipStatus> fakeResults = Arrays.asList(
                FriendshipStatus.accepted("aaaa-aaaaa-aaaa-aaaa"),
                FriendshipStatus.pending("bbbb-bbbbb-bbbbb-bbbb")
        );

        return Page.of(fakeResults, Pageable.from(0), 100L);
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

    @Error
    public HttpResponse<JsonError> handle(Exception e) {
        log.error("Unknown Error in Friendship API", e);

        var error = new JsonError("Unknown Error");
        return HttpResponse.serverError(error);
    }

}
