package it.unisannio.gruppo3.payment;
import it.unisannio.gruppo3.payment.Presentation.PaymentService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;


@ApplicationPath("payment")
@SpringBootApplication(exclude = {MongoDataAutoConfiguration.class })
public class PaymentApplication extends ResourceConfig {

    public static void main(String[] args) {
            SpringApplication.run(PaymentApplication.class, args);
        }

    public PaymentApplication(){
            register(PaymentService.class);
    }

}
