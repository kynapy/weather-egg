package com.kiyan.weathereggapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Used as an interface to LTA's Bus API.
 * Created to prevent overuse of LTA API. Goal is to cache the results for a certain period.
 */
@Service
public class LtaBusApiService {
    private final WebClient webClient;

    public LtaBusApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Gets the bus timings for a particular bus at a particular bus stop.
     * @param busStopCode code for bus stop, defined in LTA API
     * @param serviceNumber bus service number
     */
    public Object getBusTiming(int busStopCode, int serviceNumber) {
        // TODO: Cache result

        // TODO: Fix response data type
        Object response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/BusArrival")
                        .queryParam("BusStopCode", busStopCode)
                        .queryParam("ServiceNo", serviceNumber)
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.empty())
                .block();
        System.out.println(response);
        return response;
    }
}
