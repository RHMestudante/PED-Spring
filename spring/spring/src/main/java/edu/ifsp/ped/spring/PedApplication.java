package edu.ifsp.ped.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "edu.ifsp.ped.spring")
@ComponentScan(basePackages = "edu.ifsp.ped.spring")
@EnableJpaRepositories(basePackages = "edu.ifsp.ped.spring.repository")
public class PedApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedApplication.class, args);
	}

}
