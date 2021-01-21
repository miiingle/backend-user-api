package net.miiingle.user.api.client.emailsender;

import io.micronaut.context.annotation.Requires;
import lombok.extern.slf4j.Slf4j;
import net.miiingle.user.api.business.providers.EmailSender;

import javax.inject.Singleton;

@Slf4j
@Singleton
@Requires(property = "client.email", value = "error")
public class ErrorThrowingEmailSender implements EmailSender {

    @Override
    public void send(MessageRequest messageRequest) {
        log.error("Unknown Error: Failed to Send Email");
        throw new RuntimeException("Failed to send email");
    }
}
