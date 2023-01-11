package pl.lcc.listener.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//scanBasePackages = {"pl.lcc.evexample.module.processor"}

@SpringBootApplication()
@ComponentScan(basePackages = "pl.lcc.listener")
public class EventExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventExampleApplication.class, args);
	}

}
