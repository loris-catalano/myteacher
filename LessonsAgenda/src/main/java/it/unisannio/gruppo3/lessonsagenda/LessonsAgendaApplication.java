package it.unisannio.gruppo3.lessonsagenda;

import it.unisannio.gruppo3.lessonsagenda.presentation.LessonsAgendaService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@ApplicationPath("lessonsAgenda")
public class LessonsAgendaApplication extends ResourceConfig {

    public static void main(String[] args) {
        SpringApplication.run(LessonsAgendaApplication.class, args);
    }

    public LessonsAgendaApplication(){
        register(LessonsAgendaService.class);
    }


}
