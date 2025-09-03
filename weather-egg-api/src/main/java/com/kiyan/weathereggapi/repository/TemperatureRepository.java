package com.kiyan.weathereggapi.repository;

import com.kiyan.weathereggapi.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Long> {
    public TemperatureEntity findFirstByOrderByTimestampDesc();
}
