package com.bmw.microservicespring1.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author  UNGERW
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String companyURL = "http://www.nttdata.com";
	private static final String description = "Production Order demo project";
	private static final String companyName = "NTT Data";
	private static final String apiVersion = "0.0.1";
	private static final String developerName = "WUnger";
	private static final String developerEmail = "wolfgang.unger@nttdata.com";

	@Autowired
	private Environment enviornment;

	@Bean
	public Docket newsApi() {
		String microserviceName = enviornment.getProperty("spring.application.name", "production-order");
		return new Docket(DocumentationType.SWAGGER_2).groupName(microserviceName).apiInfo(apiInfo(microserviceName)).select().paths(regex("/productionorder.*")).build();
	}

	private ApiInfo apiInfo(String microserviceName) {
		return new ApiInfoBuilder().title(microserviceName).description(description).termsOfServiceUrl(companyURL).contact(getContact()).license(companyName).licenseUrl(companyURL).version(apiVersion).build();
	}

	private Contact getContact() {
		return new Contact(developerName, companyURL, developerEmail);
	}
}
