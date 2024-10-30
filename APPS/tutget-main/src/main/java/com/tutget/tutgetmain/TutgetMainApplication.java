package com.tutget.tutgetmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;


@SpringBootApplication
public class TutgetMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutgetMainApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	KeyResolver customKeyResolver() {
		return exchange -> Mono.just("1");
	}
}
