package com.kiyan.weathereggapi.service;

import com.kiyan.weathereggapi.dto.TemperatureDto;
import com.kiyan.weathereggapi.entity.TemperatureEntity;
import com.kiyan.weathereggapi.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public void uploadTemperature(TemperatureDto temperature) {
        TemperatureEntity temperatureEntity = new TemperatureEntity();
        temperatureEntity.setTemperature(temperature.getTemperature());
        temperatureEntity.setHumidity(temperature.getHumidity());
        temperatureEntity.setTimestamp(LocalDateTime.now());
        temperatureRepository.save(temperatureEntity);
    }

    public float getTemperature() {
        return temperatureRepository.findFirstByOrderByTimestampDesc().getTemperature();
    }

    public TemperatureEntity getLatestWeather() {
        return temperatureRepository.findFirstByOrderByTimestampDesc();
    }

    public TemperatureEntity getTempAndHumidityOfPastXDays(int numDays) {
        if (numDays <= 0) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastUpdate = now.minusDays(numDays);
        return temperatureRepository.findByTimestampAfterOrderByTimestampDesc(lastUpdate);
    }
}
