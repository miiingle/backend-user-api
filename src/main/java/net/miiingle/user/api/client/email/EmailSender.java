package net.miiingle.user.api.client.email;

import lombok.Builder;

public interface EmailSender {

    @Builder
    class MessageRequest {
        String emailAddress;
        String message;
    }

    void send(MessageRequest messageRequest);
}
