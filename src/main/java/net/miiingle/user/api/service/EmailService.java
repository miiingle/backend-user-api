package net.miiingle.user.api.service;

import lombok.Builder;
import lombok.Data;

public interface EmailService {

    @Builder
    class MessageRequest {
        String emailAddress;
        String message;
    }

    void send(MessageRequest messageRequest);
}
