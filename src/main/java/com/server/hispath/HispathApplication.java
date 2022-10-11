package com.server.hispath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HispathApplication {

	public static void main(String[] args) {
		SpringApplication.run(HispathApplication.class, args);
	}
}
