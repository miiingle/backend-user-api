package net.miiingle.user.api.client.email;

import io.micronaut.context.annotation.Value;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class SESEmailSender implements EmailSender {

    @Value("${test.ses.throwRandomException:false}")
    boolean throwRandomErrors;

    @Override
    public void send(MessageRequest messageRequest) {
        randomlyThrowException();
        log.info("Send Email: {}", messageRequest.emailAddress);
    }

    private void randomlyThrowException() {
        if (!throwRandomErrors) return;

        var throwException = Math.random() > 0.5;

        if (throwException) {
            log.error("Unknown Error: Failed to Send Email");
            throw new RuntimeException("Failed to send email");
        }
    }
}
