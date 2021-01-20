package net.miiingle.user.api.presentation

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import net.miiingle.user.api.client.persistence.data.UserCredential
import net.miiingle.user.api.client.persistence.data.UserProfile
import spock.lang.Specification

import javax.inject.Inject
import javax.persistence.EntityManager

@MicronautTest
class LoginSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    @Inject
    EntityManager em

    void "upon successful authentication, the user gets an access token and a refresh token"() {
        given:
        var internalId = "test01"
        var username = "sherlock"
        var password = "password"
        var userCredentials = UserCredential.builder()
                .internalId(internalId)
                .username(username)
                .password(password)
                .build()
        em.merge(userCredentials)

        var userProfile = UserProfile.builder()
                .id(internalId)
                .name("Test")
                .build()
        em.merge(userProfile)
        em.getTransaction().commit()

        when: 'Login endpoint is called with valid credentials'
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password)
        HttpRequest request = HttpRequest.POST('/login', credentials)
        BearerAccessRefreshToken rsp = client.toBlocking().retrieve(request, BearerAccessRefreshToken)

        then:
        rsp.username == username
        rsp.accessToken
        //TODO: copy this
        //https://github.com/miiingle/miiingle/blob/master/POC/demo-security/src/main/java/com/example/MyRefreshTokenStorage.java
        //rsp.refreshToken

        and: 'access token is a JWT'
        JWTParser.parse(rsp.accessToken) instanceof SignedJWT
    }
}
