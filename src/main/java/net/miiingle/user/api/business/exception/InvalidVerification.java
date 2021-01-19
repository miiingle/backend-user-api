package net.miiingle.user.api.business.exception;

public class InvalidVerification extends RuntimeException {

    public InvalidVerification() {
        super("Verification is Invalid");
    }
}
