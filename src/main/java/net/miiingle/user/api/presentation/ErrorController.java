package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import net.miiingle.user.api.business.exception.FailedToSendEmail;
import net.miiingle.user.api.presentation.data.GenericError;

@Controller
public class ErrorController {

    @Error(exception = FailedToSendEmail.class, status = HttpStatus.INTERNAL_SERVER_ERROR, global = true)
    public GenericError catchAllUnhandledException(HttpRequest<?> request, FailedToSendEmail exception) {
        return GenericError.builder()
                .code("0")
                .description("IDK what happened: " + request.getMethod() + " "
                        + request.getPath() + " (" + exception.getClass().getSimpleName() + ")")
                .build();
    }
}
