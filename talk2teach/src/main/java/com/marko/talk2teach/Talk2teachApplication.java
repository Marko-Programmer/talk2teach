package com.marko.talk2teach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Talk2teachApplication {

	public static void main(String[] args) {
		SpringApplication.run(Talk2teachApplication.class, args);
	}

}
