package com.example.linkedrhapplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API desenvolvida para um teste de empresa"))
public class LinkedrhapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedrhapplicationApplication.class, args);
	}

}
