package com.kiyan.weathereggapi.service;

import com.kiyan.weathereggapi.dto.WeatherDto;
import com.kiyan.weathereggapi.entity.WeatherEntity;
import com.kiyan.weathereggapi.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;

    WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void uploadWeather(WeatherDto weatherDto) {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setTemperature(weatherDto.temperature());
        weatherEntity.setHumidity(weatherDto.humidity());
        weatherEntity.setTimestamp(OffsetDateTime.now());
        weatherRepository.save(weatherEntity);
    }

    public WeatherEntity getLatestWeather() {
        return weatherRepository.findFirstByOrderByTimestampDesc();
    }

    public WeatherEntity getWeatherOfPastXDays(int numDays) {
        if (numDays <= 0) {
            return null;
        }
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime lastUpdate = now.minusDays(numDays);
        return weatherRepository.findByTimestampAfterOrderByTimestampDesc(lastUpdate);
    }
}
