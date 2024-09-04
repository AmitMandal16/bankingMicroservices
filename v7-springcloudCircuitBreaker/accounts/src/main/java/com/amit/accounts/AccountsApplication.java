package com.amit.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.amit.accounts.dto.AccountContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = AccountContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "AmitBank Accounts microservice REST API Documentation",
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
				description =  "AmitBank Accounts microservice REST API Documentation",
				url = "https://www.amitBank.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
