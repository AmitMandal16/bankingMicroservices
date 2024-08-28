package com.amit.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.amit.loans.dto.LoansContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableConfigurationProperties(value = LoansContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "AmitBank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Amit Mandal",
						email = "amit.mandal.2209@gmail.com",
						url = "https://www.amitBank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.amitBank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "AmitBank Loans microservice REST API Documentation",
				url = "https://www.amitBank.com/swagger-ui.html"
		)
)public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
