package net.miiingle.user.api;

import io.micronaut.http.annotation.*;

@Controller("/ping")
public class PingController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}