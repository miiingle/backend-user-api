package net.miiingle.user.api.integration.security;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import net.miiingle.user.api.business.UserRegistry;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;

@RequiredArgsConstructor
@Singleton
public class UserCredentialAuthenticationProvider implements AuthenticationProvider {

    private final UserRegistry userRegistry;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(emitter -> {
            if (authenticationRequest.getIdentity().equals("sherlock") &&
                    authenticationRequest.getSecret().equals("password")) {
                emitter.onNext(new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>()));
                emitter.onComplete();
            } else if (authenticationRequest.getIdentity().equals("admin") &&
                    authenticationRequest.getSecret().equals("admin")) {
                emitter.onNext(new UserDetails((String) authenticationRequest.getIdentity(), Collections.singletonList("ADMIN")));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }
}
