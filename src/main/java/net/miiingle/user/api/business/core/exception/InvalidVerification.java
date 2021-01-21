package net.miiingle.user.api.business.core.exception;

public class InvalidVerification extends RuntimeException {

    public InvalidVerification() {
        super("Verification is Invalid");
    }
}
