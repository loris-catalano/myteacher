package it.unisannio.gruppo3.myteachergateway;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import it.unisannio.gruppo3.myteachergateway.presentation.GatewayService;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@ApplicationPath("myTeacher")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class MyTeacherGatewayApplication extends ResourceConfig {
	public static void main(String[] args) {
		SpringApplication.run(MyTeacherGatewayApplication.class, args);
	}

	public MyTeacherGatewayApplication(){register(GatewayService.class);}
}
