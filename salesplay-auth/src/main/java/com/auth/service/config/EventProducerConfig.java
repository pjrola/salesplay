package com.auth.service.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProducerConfig {

    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

}