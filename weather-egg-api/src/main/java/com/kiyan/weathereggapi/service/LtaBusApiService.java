package com.kiyan.weathereggapi.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.antlr.v4.runtime.misc.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final HazelcastInstance hazelcastInstance;
    private static final Logger logger = LoggerFactory.getLogger(LtaBusApiService.class);

    public LtaBusApiService(WebClient webClient, HazelcastInstance hazelcastInstance) {
        this.webClient = webClient;
        this.hazelcastInstance = hazelcastInstance;
    }

    /**
     * Gets the bus timings for a particular bus at a particular bus stop.
     * @param busStopCode code for bus stop, defined in LTA API
     * @param serviceNumber bus service number
     */
    public String getBusTiming(int busStopCode, int serviceNumber) {
        IMap<Pair<Integer, Integer>, String> busArrivalCache = this.hazelcastInstance.getMap("lta-bus-arrival");
        Pair<Integer, Integer> input = new Pair<>(busStopCode, serviceNumber);
        String response;
        response = busArrivalCache.get(input);
        if (response != null) {
            logger.info("Found in cache, returning from cache.");
            return response;
        }

        logger.info("Data not found in cache, hitting LTA's API");
        response = this.webClient.get()
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
        if (response != null) {
            busArrivalCache.put(input, response);
        }
        return response;
    }
}
