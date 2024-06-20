package it.unisannio.gruppo3.review;

import it.unisannio.gruppo3.review.presentation.ReviewService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class })
@ApplicationPath("review")
public class ReviewApplication extends ResourceConfig {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}

	public ReviewApplication(){
		register(ReviewService.class);
	}

}
