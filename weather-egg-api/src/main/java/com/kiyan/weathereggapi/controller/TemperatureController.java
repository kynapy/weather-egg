package com.kiyan.weathereggapi.controller;

import com.kiyan.weathereggapi.dto.TemperatureDto;
import com.kiyan.weathereggapi.service.TemperatureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {
    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping("/temperature")
    public void uploadTemperature(@RequestBody TemperatureDto temperature) {
        temperatureService.uploadTemperature(temperature);
    }

    @GetMapping("/temperature")
    public float getTemperature(q) {
        System.out.println("Getting temperature");
        return temperatureService.getTemperature();
    }
}