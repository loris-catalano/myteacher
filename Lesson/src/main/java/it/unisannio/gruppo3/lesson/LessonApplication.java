package it.unisannio.gruppo3.lesson;

import it.unisannio.gruppo3.lesson.Presentation.LessonService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class })
@ApplicationPath("lesson")
public class LessonApplication extends ResourceConfig {

    public static void main(String[] args) {
        SpringApplication.run(LessonApplication.class, args);
    }

    public LessonApplication(){
        register(LessonService.class);
    }

}
