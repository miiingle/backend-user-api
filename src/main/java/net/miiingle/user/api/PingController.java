package net.miiingle.user.api;

import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Controller("/ping")
public class PingController {

    @Get(uri="/nothing", produces="text/plain")
    public String pingNothing() {
        log.info("Ping: {}", LocalDateTime.now());
        return "Pong";
    }
}