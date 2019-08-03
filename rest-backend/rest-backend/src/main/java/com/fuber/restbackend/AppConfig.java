package com.fuber.restbackend;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DateTimeFormatter formatter() {
        return DateTimeFormat.forPattern("yyyyMMdd");
    }

}
