package com.kiyan.weathereggapi.service;

import com.kiyan.weathereggapi.entity.Temperature;
import com.kiyan.weathereggapi.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public void uploadTemperature(Temperature temperature) {
        temperatureRepository.save(temperature);
    }

    public float getTemperature() {
        return temperatureRepository.findFirstByOrderByIdDesc().getTemperature();
    }
}
