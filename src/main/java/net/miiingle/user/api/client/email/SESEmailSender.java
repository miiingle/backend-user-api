package net.miiingle.user.api.client.email;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class SESEmailSender implements EmailSender {

    @Override
    public void send(MessageRequest messageRequest) {
        var throwException = Math.random() > 0.5;

        if (throwException) {
            log.error("Unknown Error: Failed to Send Email");
            throw new RuntimeException("Failed to send email");
        }

        log.info("Send Email: {}", messageRequest.emailAddress);
    }
}
