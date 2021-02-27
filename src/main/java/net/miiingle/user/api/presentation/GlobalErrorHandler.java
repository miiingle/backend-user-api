package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class GlobalErrorHandler {

    @Error(exception = AuthorizationException.class, global = true)
    public HttpResponse<JsonError> catchAuthorizationExceptions(HttpRequest<?> request, AuthorizationException exception) {

        var error = new JsonError("Authorization Invalid: You will need to reauthenticate");
        error.link(Link.SELF, Link.of(request.getUri()));
        error.link("login", Link.of("/login"));

        return HttpResponse
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }

    @Error(exception = Exception.class, global = true)
    public HttpResponse<JsonError> catchAllUnhandledException(HttpRequest<?> request, Exception exception) {

        var message = "IDK what happened: " + request.getMethod() + " "
                + request.getPath() + " (" + exception.getClass().getSimpleName() + ")";

        var error = new JsonError(message);
        error.link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.serverError(error);
    }

}
