package it.unisannio.gruppo3.student;

import it.unisannio.gruppo3.student.presentation.StudentService;
import jakarta.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class })
@ApplicationPath("student")
public class StudentApplication extends ResourceConfig {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    public StudentApplication(){
        register(StudentService.class);
    }
}
