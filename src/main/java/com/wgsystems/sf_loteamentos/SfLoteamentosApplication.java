package com.wgsystems.sf_loteamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SfLoteamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfLoteamentosApplication.class, args);
	}

}
