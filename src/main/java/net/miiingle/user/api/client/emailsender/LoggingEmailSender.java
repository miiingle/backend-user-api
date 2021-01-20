package net.miiingle.user.api.client.emailsender;

import io.micronaut.context.annotation.Requires;
import lombok.extern.slf4j.Slf4j;
import net.miiingle.user.api.business.service.EmailSender;

import javax.inject.Singleton;

@Slf4j
@Singleton
@Requires(property = "client.email", value = "logging")
public class LoggingEmailSender implements EmailSender {

    @Override
    public void send(MessageRequest messageRequest) {
        log.info("Send Email: {} : {}", messageRequest.getEmailAddress(), messageRequest.getMessage());
    }

}
