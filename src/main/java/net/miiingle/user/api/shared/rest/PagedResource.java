package net.miiingle.user.api.shared.rest;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.hateoas.AbstractResource;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Value
@Introspected
public class PagedResource<T> extends AbstractResource<PagedResource<T>> {

    @Value
    @Introspected
    public static class Page {

        Integer current;
        Integer count;
        Long total;

        public static Page from(io.micronaut.data.model.Page<?> page) {
            return new Page(page.getPageNumber(), page.getTotalPages(), page.getTotalSize());
        }
    }

    List<T> contents;
    Page page;

    public static <T> PagedResource<T> from(io.micronaut.data.model.Page<T> pageParam) {
        Page page = Page.from(pageParam);
        return new PagedResource<T>(pageParam.getContent(), page);
    }
}
