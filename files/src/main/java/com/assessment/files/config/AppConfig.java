package com.assessment.files.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public Queue myQueue() {
        return new Queue("files", false);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
