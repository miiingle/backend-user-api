package net.miiingle.user.api.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import net.miiingle.user.api.entity.Registration
import spock.lang.Specification

import javax.inject.Inject


@MicronautTest
class RegistrationControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    def "should register"() {

        given:
        HttpRequest request = HttpRequest.POST("/register", new Registration())

        when:
        HttpResponse response = client.toBlocking().exchange(request)

        then:
        response.status == HttpStatus.OK
    }
}
