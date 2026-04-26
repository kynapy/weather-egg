package com.kiyan.weathereggapi.controller;

import com.kiyan.weathereggapi.dto.TemperatureDto;
import com.kiyan.weathereggapi.entity.TemperatureEntity;
import com.kiyan.weathereggapi.service.TemperatureService;
import org.springframework.web.bind.annotation.*;

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
    public float getLatestTemperature() {
        return temperatureService.getTemperature();
    }

    @GetMapping("/current-weather")
    public TemperatureEntity getLatestWeather() {
        return temperatureService.getLatestWeather();
    }

    @GetMapping("/weather")
    public TemperatureEntity getWeatherDetails(@RequestParam("pastDays") int numDays) {
        return temperatureService.getTempAndHumidityOfPastXDays(numDays);
    }
}