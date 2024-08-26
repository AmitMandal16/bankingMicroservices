package com.amit.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "AmitBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Amit Mandal",
						email = "amit.mandal.2209@gmail.com",
						url = "https://www.amitbank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.amitbank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Amitbank Cards microservice REST API Documentation",
				url = "https://www.amitbank.com/swagger-ui.html"
		)
)public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
