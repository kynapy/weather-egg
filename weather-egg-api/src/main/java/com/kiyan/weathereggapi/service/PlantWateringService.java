package com.kiyan.weathereggapi.service;

import com.kiyan.weathereggapi.dto.LastWaterResponse;
import com.kiyan.weathereggapi.dto.WaterPlantDto;
import com.kiyan.weathereggapi.entity.PlantWateringEntity;
import com.kiyan.weathereggapi.repository.PlantWateringRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PlantWateringService {
    private final PlantWateringRepository plantWateringRepository;

    public PlantWateringService(PlantWateringRepository plantWateringRepository) {
        this.plantWateringRepository = plantWateringRepository;
    }

    public void waterPlant(WaterPlantDto waterPlantDto) {
        LocalDateTime now = LocalDateTime.now();
        PlantWateringEntity plantWateringEntity = new PlantWateringEntity();
        plantWateringEntity.setWaterAmount(waterPlantDto.waterAmount());
        plantWateringEntity.setWateringTime(now);
        plantWateringRepository.save(plantWateringEntity);
    }

    public LastWaterResponse getLastWatering() {
        PlantWateringEntity plantWateringEntity = plantWateringRepository.findFirstOrderByWateringTimeDesc();
        return new LastWaterResponse(plantWateringEntity.getWaterAmount(), plantWateringEntity.getWateringTime());
    }
}
