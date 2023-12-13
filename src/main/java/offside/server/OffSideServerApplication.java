package offside.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OffSideServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffSideServerApplication.class, args);
	}
}