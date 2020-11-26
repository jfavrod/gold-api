package com.jasonfavrod.gold;

import com.jasonfavrod.gold.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
public class GoldApplication {
	ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

	public static void main(String[] args) {
		SpringApplication.run(GoldApplication.class, args);
	}

	@RestController
	@RequestMapping("/")
	public class TestController {
		@GetMapping
		public String getHello() {
			return "Hello World!";
		}
	}
}
