package net.miiingle.user.api.ping;

import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Controller("/ping")
public class PingController {

    private final PingRepository repository;

    @Operation(
            tags = "Ping"
    )
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri="/server", produces="text/plain")
    public String pingNothing() {
        log.info("Ping Server: {}", LocalDateTime.now());
        return "Pong";
    }

    @Operation(
            tags = "Ping"
    )
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri="/postgres", produces="text/plain")
    public String create() {
        var now = LocalDateTime.now();
        var newPing = repository.save(new PingEntity(null, "X"+now, now));

        log.info("Created: {}", newPing);

        return "Pong " + newPing.id;
    }

    @Operation(
            tags = "Ping"
    )
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(uri = "/security")
    public Authentication security(Authentication authentication) {
        log.info("Ping Security: {}", authentication.getName());
        return authentication;
    }
}