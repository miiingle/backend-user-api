package net.miiingle.user.api;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Miiingle.NET User API",
                version = "1.0",
                description = "Miiingle.NET User API",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://startupbuilder.info", name = "startupbuilder.info", email = "contact@startupbuilder.info")
        ),
        servers = {
                @Server(
                        description = "Local Dev",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Dev",
                        url = "https://dev.api.miiingle.net"
                )
        },
        tags = {
                @Tag(name = "Ping"),
                @Tag(name = "User Profile"),
                @Tag(name = "Friendship")
        }
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
