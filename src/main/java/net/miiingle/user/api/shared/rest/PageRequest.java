package net.miiingle.user.api.shared.rest;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.QueryValue;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
@Introspected
public class PageRequest {

    @Nullable
    @Positive
    @QueryValue
    private Integer page;

    @Nullable
    @Positive
    @QueryValue
    private Integer size;
}
