package net.miiingle.user.api.presentation;

import brave.SpanCustomizer;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.core.UserRegistry;
import net.miiingle.user.api.business.core.data.RegistrationRequest;
import net.miiingle.user.api.presentation.data.RegistrationVerificationDTO;

@RequiredArgsConstructor
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/registrations")
public class RegistrationController {

    private final UserRegistry userRegistry;
    private final SpanCustomizer spanCustomizer;

    @Operation(
            operationId = "registrationStart",
            summary = "Initiate the Registration process",
            description = "Starts the process of a user registration. At this point no user account is created, yet",
            tags = {"Registration"}
    )
    @Post("/")
    @Status(HttpStatus.CREATED)
    public void startRegistration(@Body RegistrationRequest registrationRequest) {

        //TODO: make this hack official
        //hack source
        //https://github.com/openzipkin/zipkin-aws/blob/master/storage/xray-udp/src/main/java/zipkin2/storage/xray_udp/UDPMessageEncoder.java
        spanCustomizer.tag("http.url", "https://dev.miiingle.net");
        spanCustomizer.tag("http.user_agent", "ios13");
        spanCustomizer.tag("http.client_ip", "122.1.2.2");
        spanCustomizer.tag("http.status_code", "201");

        userRegistry.register(registrationRequest);
    }

    @Operation(
            operationId = "registrationVerify",
            summary = "Complete the registration process",
            description = "Verifies the provided user details and creates the user profile and login",
            tags = {"Registration"}
    )
    @Get("/{registrationId}/verify{?code}")
    @Status(HttpStatus.OK)
    public void verifyRegistration(@RequestBean RegistrationVerificationDTO verification) {
        userRegistry.verify(verification.convert());
    }
}
