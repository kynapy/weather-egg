package com.kiyan.weathereggapi.controller;

import com.kiyan.weathereggapi.dto.BusArrivalQueryDto;
import com.kiyan.weathereggapi.service.LtaBusApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class LtaBusApiController {
    private final LtaBusApiService ltaBusApiService;
    public LtaBusApiController(LtaBusApiService ltaBusApiService) {
        this.ltaBusApiService = ltaBusApiService;
    }

    @GetMapping("/bus-arrival")
    public ResponseEntity<Object> getBusTiming(@RequestParam(value = "busStopCode") int busStopCode, @RequestParam(value = "serviceNumber") int serviceNumber) {
        Object response = ltaBusApiService.getBusTiming(busStopCode, serviceNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
