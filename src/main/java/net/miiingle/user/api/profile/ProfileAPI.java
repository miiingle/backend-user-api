package net.miiingle.user.api.profile;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller("/profile")
public class ProfileAPI {

    @Operation(
            tags = "User Profile"
    )
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri="/{userId}")
    public PublicProfile displayPublicProfile(String userId) {
        return new PublicProfile(userId, "Test Test");
    }
}
