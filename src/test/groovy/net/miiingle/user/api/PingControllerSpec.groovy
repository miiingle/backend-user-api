package net.miiingle.user.api

import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Shared

import javax.inject.Inject

@MicronautTest
class PingControllerSpec extends Specification {

    @Shared @Inject
    EmbeddedServer embeddedServer

    @Shared @AutoCleanup @Inject @Client("/")
    RxHttpClient client

    void "server is ready"() {
        given:
        HttpResponse response = client.toBlocking().exchange("/ping/server")

        expect:
        response.status == HttpStatus.OK
    }

    void "database is ready"() {
        given:
        HttpResponse response = client.toBlocking().exchange("/ping/postgres")

        expect:
        response.status == HttpStatus.OK
    }

    void "security is ready"() {
        when:
        client.toBlocking().exchange("/ping/security")

        then:
        def httpError = thrown(HttpClientResponseException)
        httpError.getStatus() == HttpStatus.UNAUTHORIZED
    }
}
