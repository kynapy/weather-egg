package com.kiyan.weathereggapi.repository;

import com.kiyan.weathereggapi.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    public Temperature findFirstByOrderByIdDesc();
}
