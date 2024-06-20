package it.unisannio.gruppo3.user;

import it.unisannio.gruppo3.user.presentation.UserService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class })
@ApplicationPath("user")
public class UserApplication extends ResourceConfig {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    public UserApplication(){register(UserService.class);}
}
