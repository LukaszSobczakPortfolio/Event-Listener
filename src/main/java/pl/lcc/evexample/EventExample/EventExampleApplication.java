package pl.lcc.evexample.EventExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//scanBasePackages = {"pl.lcc.evexample.module.processor"}
@SpringBootApplication()

public class EventExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventExampleApplication.class, args);
	}

}
