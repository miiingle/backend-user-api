package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import net.miiingle.user.api.presentation.data.shared.ResourceError;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class GlobalErrorHandler {

    @Error(exception = Exception.class, global = true)
    public HttpResponse<ResourceError> catchAllUnhandledException(HttpRequest<?> request, Exception exception) {

        var error= ResourceError.builder()
                .code("0")
                .description("IDK what happened: " + request.getMethod() + " "
                        + request.getPath() + " (" + exception.getClass().getSimpleName() + ")")
                .build();

        return HttpResponse.serverError(error);
    }
}
