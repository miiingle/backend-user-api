package net.miiingle.user.api.shared.rest;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.QueryValue;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Introspected
public class PageRequest {

    @Nullable
    @PositiveOrZero
    @QueryValue
    private Integer page;

    @Nullable
    @Positive
    @QueryValue
    @Max(100)
    private Integer size;

    public Pageable asPageable() {
        return Pageable.from(page!=null?page:0, size!=null?size:20);
    }
}
