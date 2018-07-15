package com.crypto.CryptoCurrency;

import com.crypto.CryptoCurrency.utils.SchedulingTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class CryptoCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoCurrencyApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean
	public SchedulingTasks task() {
		return new SchedulingTasks();
	}
}
