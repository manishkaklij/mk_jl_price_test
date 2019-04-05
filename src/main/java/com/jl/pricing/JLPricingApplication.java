package com.jl.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Manish Kaklij
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class JLPricingApplication {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JLPricingApplication.class, args);
	}

}
