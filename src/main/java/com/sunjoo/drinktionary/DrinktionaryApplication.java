package com.sunjoo.drinktionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.sunjoo.drinktionary.client")
public class DrinktionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrinktionaryApplication.class, args);
	}

}
