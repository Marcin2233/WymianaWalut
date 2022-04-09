package com.kwiatkowski.WymianaWalut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class WymianaWalutApplication {

	public static void main(String[] args) {
		SpringApplication.run(WymianaWalutApplication.class, args);
	}

	@Bean
	public Docket get()
	{
		ApiInfo apiInfo = new ApiInfo("Wymiana walut API documentation", "", "1.0", ""
				, new Contact("Marcin Kwiatkowski", "", "kwiatkowski.marcin22@gmail.com"), "", "", Collections.emptyList());
		;
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.kwiatkowski.WymianaWalut.controller"))
				.build().apiInfo(apiInfo);
	}

}
