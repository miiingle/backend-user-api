package net.miiingle.user.api.client.email;

import io.micronaut.context.annotation.Requires;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
@Requires(property = "client.email", value = "logging")
public class LoggingEmailSender implements EmailSender {

    @Override
    public void send(MessageRequest messageRequest) {
        log.info("Send Email: {}", messageRequest.emailAddress);
    }

}
