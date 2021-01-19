package net.miiingle.user.api.presentation

import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import net.miiingle.user.api.business.data.RegistrationRequest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
@Property(name = "client.email", value = "error")
class RegistrationControllerWithErrorSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    def "should throw a generic error when an exception is encountered"() {

        given:
        HttpRequest request = HttpRequest.POST("/registration", RegistrationRequest.builder().build())

        when:
        client.toBlocking().exchange(request)

        then:
        def response = thrown(HttpClientResponseException)
        response.status == HttpStatus.INTERNAL_SERVER_ERROR
    }
}
