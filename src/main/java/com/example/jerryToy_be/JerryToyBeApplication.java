package com.example.jerryToy_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.jerryToy_be.Repository")
public class JerryToyBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JerryToyBeApplication.class, args);
	}

}
