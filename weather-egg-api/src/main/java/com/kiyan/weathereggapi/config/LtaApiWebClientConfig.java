package com.kiyan.weathereggapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LtaApiWebClientConfig {
    @Value("${LTA_API_URI}")
    private String LTA_API_URI;

    @Value("${LTA_API_KEY}")
    private String LTA_API_KEY;

    @Bean
    public WebClient getLtaWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(LTA_API_URI)
                .defaultHeader("AccountKey", LTA_API_KEY)
                .build();
    }
}
