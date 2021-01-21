package net.miiingle.user.api.integration.security;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.core.UserRegistry;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

@RequiredArgsConstructor
@Singleton
public class UserCredentialAuthenticationProvider implements AuthenticationProvider {

    private final UserRegistry userRegistry;

    //TODO: integrate in openAPI
    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(getAuthenticationResponseFlowableOnSubscribe(authenticationRequest), BackpressureStrategy.ERROR);
    }

    @NonNull
    private FlowableOnSubscribe<AuthenticationResponse> getAuthenticationResponseFlowableOnSubscribe(AuthenticationRequest<?, ?> authenticationRequest) {
        return emitter -> {

            var email = authenticationRequest.getIdentity() instanceof String
                    ? (String) authenticationRequest.getIdentity() : "";
            var password = authenticationRequest.getSecret() instanceof String
                    ? (String) authenticationRequest.getSecret() : "";

            var user = userRegistry.fetchUserWith(email);

            if (user.isPresent() && user.get().validatesToCredentials(email, password)) {
                emitter.onNext(AppUserDetails.from(user.get()));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        };
    }
}
