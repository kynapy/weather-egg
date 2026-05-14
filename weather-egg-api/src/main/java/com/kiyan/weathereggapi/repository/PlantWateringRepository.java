package com.kiyan.weathereggapi.repository;

import com.kiyan.weathereggapi.entity.PlantWateringEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantWateringRepository extends JpaRepository<PlantWateringEntity, Long> {
    PlantWateringEntity findFirstByOrderByWateringTimeDesc();
}
