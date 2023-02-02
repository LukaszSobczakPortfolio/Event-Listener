package pl.lcc.listener.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication()
@Import(pl.lcc.listener.module.DispatcherModuleConfiguration.class)
public class EventExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventExampleApplication.class, args);
	}

}
