package com.codestates.culinari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CulinariApplication {

	public static void main(String[] args) {
		SpringApplication.run(CulinariApplication.class, args);
	}

}
