package net.miiingle.user.api.presentation

import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import net.miiingle.user.api.business.data.RegistrationRequest
import net.miiingle.user.api.business.data.RegistrationVerification
import net.miiingle.user.api.client.persistence.data.Registration
import spock.lang.Specification

import javax.inject.Inject
import javax.persistence.EntityManager


@MicronautTest
@Property(name = "client.email", value = "logging")
class RegistrationControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    @Inject
    EntityManager em

    def "should register"() {

        given:
        HttpRequest request = HttpRequest.POST("/registrations", RegistrationRequest.builder()
                .name("Test Test")
                .email("test@miiingle.net")
                .build())

        when:
        HttpResponse response = client.toBlocking().exchange(request)

        then:
        response.status == HttpStatus.CREATED
    }

    def "should confirm registration"() {

        given:
        var registrationId = 1
        var confirmationCode = "0000000"
        Registration registration = Registration.builder()
                .id(registrationId)
                .confirmationCode(confirmationCode)
                .confirmed(false)
                .build()
        em.merge(registration)
        em.getTransaction().commit()

        and:
        RegistrationVerification verification = RegistrationVerification.builder()
                .registrationId(registrationId.toString())
                .code(confirmationCode)
                .build()
        HttpRequest request = HttpRequest.GET("/registrations/$registrationId/verify?code=$confirmationCode")

        when:
        HttpResponse response = client.toBlocking().exchange(request)

        then:
        response.status == HttpStatus.OK

    }

}
