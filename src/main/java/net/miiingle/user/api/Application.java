package net.miiingle.user.api;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "jwt"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Miiingle.NET User API",
                version = "1.0-BETA",
                description = "API for regular users using the Miiingle.NET social media platform"
        ),
        servers = {
                @Server(
                        description = "Local",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Development",
                        url = "http://api.dev.miiingle.net"
                ),
                @Server(
                        description = "Production",
                        url = "https://api.miiingle.net"
                )
        },
        security = {
                @SecurityRequirement(name = "BearerAuth")
        }
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
