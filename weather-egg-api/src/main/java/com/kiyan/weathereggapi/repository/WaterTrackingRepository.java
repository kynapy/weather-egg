package com.kiyan.weathereggapi.repository;

import com.kiyan.weathereggapi.entity.WaterTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterTrackingRepository extends JpaRepository<WaterTrackingEntity, Long> {
    WaterTrackingEntity findFirstByOrderByTimestampDesc();
}
