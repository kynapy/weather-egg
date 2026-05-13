package com.kiyan.weathereggapi.service;

import com.kiyan.weathereggapi.entity.WaterTrackingEntity;
import com.kiyan.weathereggapi.repository.WaterTrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class WaterTrackingService {
    WaterTrackingRepository waterTrackingRepository;

    public WaterTrackingService(WaterTrackingRepository waterTrackingRepository) {
        this.waterTrackingRepository = waterTrackingRepository;
    }

    public float getRemainingWater() {
        return waterTrackingRepository.findFirstOrderByTimestampDesc().getWaterAmount();
    }

    public void uploadWaterAmount(WaterTrackingEntity waterTrackingEntity) {
        waterTrackingRepository.save(waterTrackingEntity);
    }
}
