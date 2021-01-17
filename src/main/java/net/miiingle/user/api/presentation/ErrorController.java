package net.miiingle.user.api.presentation;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Status;
import net.miiingle.user.api.presentation.data.GenericError;

//@Controller
//public class ErrorController {
//
//    @Status(HttpStatus.INTERNAL_SERVER_ERROR)
//    @Error(exception = Exception.class)
//    public GenericError catchAllUnhandledException(HttpRequest request, RuntimeException exception) {
//        return GenericError.builder()
//                .code("0")
//                .description("IDK what happened")
//                .build();
//    }
//}
