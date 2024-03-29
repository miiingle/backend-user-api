package net.miiingle.user.api.profile;

import io.micronaut.data.model.Page;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.miiingle.user.api.profile.core.ProfileDoesNotExist;
import net.miiingle.user.api.profile.core.ProfileService;
import net.miiingle.user.api.profile.core.UserProfile;
import net.miiingle.user.api.shared.rest.PageRequest;
import net.miiingle.user.api.shared.rest.PagedResource;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.stream.Collectors;

@Tag(name = "User Profile")
@RequiredArgsConstructor
@Slf4j
@Controller("/profile")
public class ProfileAPI {

    private final SecurityService securityService;
    private final ProfileService profileService;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri="/{userId}")
    public PublicProfile displayPublicProfile(String userId) {
        return new PublicProfile(profileService.findById(userId));
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/search/byName")
    public PagedResource<PublicProfile> searchByName(@NotBlank String name, @Valid @RequestBean PageRequest page) {
        var results = profileService.searchByName(name, page.asPageable());
        var contents = results.getContent().stream().map(PublicProfile::new).collect(Collectors.toList());

        return PagedResource.from(Page.of(contents, results.getPageable(), results.getTotalSize()));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(uri = "/me")
    public PublicProfile showMyProfile() {
        return new PublicProfile(profileService.findById(extractedUserId()));
    }

    private String extractedUserId() {
        return securityService.getAuthentication()
                .orElseThrow(ProfileDoesNotExist::create)
                .getName();
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put(uri = "/me")
    public void updateMyProfile(UpdateProfile command) {
        log.info("Update Profile [{}]: {}", extractedUserId(), command);
        profileService.update(new UserProfile(extractedUserId(), command.getFullName()));
    }

    @Error
    public HttpResponse<JsonError> profileNotFound(ProfileDoesNotExist e) {
        var error = new JsonError("Profile does not exist: " + e.getMessage());

        return HttpResponse.notFound(error);
    }
}
