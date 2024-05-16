package it.unisannio.gruppo3.teacher;

import it.unisannio.gruppo3.teacher.presentation.TeacherService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

@SpringBootApplication(exclude = {MongoDataAutoConfiguration.class })
@ApplicationPath("teacher")
public class TeacherApplication extends ResourceConfig {

    public static void main(String[] args) {
        SpringApplication.run(TeacherApplication.class, args);
    }
    public TeacherApplication(){
        register(TeacherService.class);
    }
}
