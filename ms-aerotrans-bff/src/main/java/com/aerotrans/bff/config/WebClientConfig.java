package com.aerotrans.bff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "transfersWebClient")
    public WebClient transfersWebClient(@Value("${microservices.transfers.url}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    @Bean(name = "catalogWebClient")
    public WebClient catalogWebClient(@Value("${microservices.catalog.url}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
