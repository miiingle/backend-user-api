package net.miiingle.user.api.ping;

import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Controller("/ping")
public class PingController {

    private final PingRepository repository;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri="/nothing", produces="text/plain")
    public String pingNothing() {
        log.info("Ping: {}", LocalDateTime.now());
        return "Pong";
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri="/create", produces="text/plain")
    public String create() {
        var now = LocalDateTime.now();
        var newPing = repository.save(new PingEntity(null, "X"+now, now));

        log.info("Created: {}", newPing);

        return "Pong " + newPing.id;
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(uri = "/security")
    public Authentication security(Authentication authentication) {
        return authentication;
    }
}