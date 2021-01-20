package net.miiingle.user.api.business.service;

import lombok.Builder;
import lombok.Data;

public interface EmailSender {

    @Data
    @Builder
    class MessageRequest {
        String emailAddress;
        String message;
    }

    void send(MessageRequest messageRequest);
}
