package net.miiingle.user.api.profile;

import lombok.Getter;
import net.miiingle.user.api.profile.core.UserProfile;

@Getter
public class PublicProfile {

    String id;
    String name;

    public PublicProfile(UserProfile fullProfile) {
        this.id = fullProfile.getId();
        this.name = fullProfile.getFullName();
    }
}
