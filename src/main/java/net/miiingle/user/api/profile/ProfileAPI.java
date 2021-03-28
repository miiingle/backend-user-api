package net.miiingle.user.api.profile;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.miiingle.user.api.profile.core.ProfileDoesNotExist;

@Tag(name = "User Profile")
@RequiredArgsConstructor
@Slf4j
@Controller("/profile")
public class ProfileAPI {

    private final SecurityService securityService;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri="/{userId}")
    public PublicProfile displayPublicProfile(String userId) {
        if (userId.equalsIgnoreCase("xx")) throw ProfileDoesNotExist.create();

        return new PublicProfile(userId, "Test Test");
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(uri = "/me")
    public PublicProfile showMyProfile() {
        return new PublicProfile(securityService.getAuthentication().get().getName(), "Test Test");
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put(uri = "/me")
    public void updateMyProfile(UpdateProfile command) {
        log.info("Update Profile [{}]: {}", securityService.getAuthentication().get().getName(), command);
    }

    @Error
    public HttpResponse<JsonError> profileNotFound(IllegalStateException e) {
        var error = new JsonError("Profile does not exist: " + e.getMessage());

        return HttpResponse.notFound(error);
    }
}
