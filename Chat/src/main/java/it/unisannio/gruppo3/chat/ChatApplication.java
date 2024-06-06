package it.unisannio.gruppo3.chat;

import it.unisannio.gruppo3.chat.Presentation.ChatService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

@SpringBootApplication(exclude = {MongoDataAutoConfiguration.class })
@ApplicationPath("chat")
public class ChatApplication extends ResourceConfig {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    public ChatApplication(){
        register(ChatService.class);
    }

}
