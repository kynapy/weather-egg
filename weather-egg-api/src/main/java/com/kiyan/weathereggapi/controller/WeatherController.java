package com.kiyan.weathereggapi.controller;

import com.kiyan.weathereggapi.dto.WeatherDto;
import com.kiyan.weathereggapi.entity.WeatherEntity;
import com.kiyan.weathereggapi.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/weather")
    public void uploadWeather(@RequestBody WeatherDto weatherDto) {
        weatherService.uploadWeather(weatherDto);
    }

    @GetMapping("/current-weather")
    public WeatherEntity getLatestWeather() {
        return weatherService.getLatestWeather();
    }

    @GetMapping("/weather")
    public WeatherEntity getWeatherDetails(@RequestParam("pastDays") int numDays) {
        return weatherService.getWeatherOfPastXDays(numDays);
    }

    @GetMapping("/temperature")
    public float getLatestTemperature() {
        return weatherService.getLatestWeather().getTemperature();
    }
}