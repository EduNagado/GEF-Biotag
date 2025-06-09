package com.gef_biotag.Biotag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "GEF API", version = "v1", description = "API do projeto GEF, Global Solution ", contact = @Contact(name = "Eduardo Nagado", email = "eduardo.nagado@gmail.com")))
public class BiotagApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiotagApplication.class, args);
	}

	//http://localhost:8080/swagger-ui/index.html#/
}
