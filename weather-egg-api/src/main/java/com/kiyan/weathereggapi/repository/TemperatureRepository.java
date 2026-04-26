package com.kiyan.weathereggapi.repository;

import com.kiyan.weathereggapi.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Long> {
    public TemperatureEntity findFirstByOrderByTimestampDesc();
    public TemperatureEntity findByTimestampAfterOrderByTimestampDesc(LocalDateTime created);
}