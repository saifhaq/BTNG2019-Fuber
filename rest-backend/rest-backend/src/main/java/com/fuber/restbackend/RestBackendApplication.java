package com.fuber.restbackend;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestBackendApplication.class, args);
	}

}
