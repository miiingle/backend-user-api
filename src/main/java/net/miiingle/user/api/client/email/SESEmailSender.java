package net.miiingle.user.api.client.email;

import net.miiingle.user.api.client.email.EmailSender;

import javax.inject.Singleton;

@Singleton
public class SESEmailSender implements EmailSender {

    @Override
    public void send(MessageRequest messageRequest) {
        //TODO: implement
    }
}
