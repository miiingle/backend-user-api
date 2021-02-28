package net.miiingle.user.api.presentation.hateos.support;

import io.micronaut.http.hateoas.Link;
import net.miiingle.user.api.presentation.hateos.IdentifierResource;

public class RegistrationResourceBuilder {

    public static IdentifierResource build(Long registrationId) {
        var newlyCreated = IdentifierResource.create(registrationId.toString());
        newlyCreated.link("verify", Link.build("/{registrationId}/verify{?code}")
                .name("VerifyRegistration")
                .title("Verify the New Registration")
                .templated(true)
                .build());

        return newlyCreated;
    }
}
