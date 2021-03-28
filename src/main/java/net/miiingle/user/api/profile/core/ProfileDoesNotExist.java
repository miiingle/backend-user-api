package net.miiingle.user.api.profile.core;

public class ProfileDoesNotExist extends IllegalStateException {

    public ProfileDoesNotExist() {
        super("User profile does not exist");
    }

    public static ProfileDoesNotExist create() {
        return new ProfileDoesNotExist();
    }
}
