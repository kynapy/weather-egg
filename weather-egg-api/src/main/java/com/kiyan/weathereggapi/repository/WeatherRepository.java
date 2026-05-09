package com.kiyan.weathereggapi.repository;

import com.kiyan.weathereggapi.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    public WeatherEntity findFirstByOrderByTimestampDesc();
    public WeatherEntity findByTimestampAfterOrderByTimestampDesc(LocalDateTime created);
}