package net.miiingle.user.api.business.exception;

public class FailedToSendEmail extends RuntimeException {

    public FailedToSendEmail() {
        super("Failed to send email due to unknown reason");
    }
}
