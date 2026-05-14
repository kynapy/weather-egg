package com.kiyan.weathereggapi.controller;

import com.kiyan.weathereggapi.dto.LastWaterResponse;
import com.kiyan.weathereggapi.dto.WaterPlantDto;
import com.kiyan.weathereggapi.mqtt.MqttConfig;
import com.kiyan.weathereggapi.service.PlantWateringService;
import com.kiyan.weathereggapi.service.WaterTrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/plant")
public class PlantController {
    private final WaterTrackingService waterTrackingService;
    private final PlantWateringService plantWateringService;
    private final MqttConfig.MqttOutputChannel mqttOutputChannel;
    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

    public PlantController(WaterTrackingService waterTrackingService, MqttConfig.MqttOutputChannel mqttOutputChannel,  PlantWateringService plantWateringService) {
        this.waterTrackingService = waterTrackingService;
        this.mqttOutputChannel = mqttOutputChannel;
        this.plantWateringService = plantWateringService;
    }

    /**
     * Endpoint to trigger watering of the plant.
     * This should store the current watered amount + the time of the triggering, and send a MQTT
     * message to the IoT device/topic responsible for triggering the watering.
     * TODO: It should accept either a duration to pump or an amount to pump, depending on the device we are using.
     *
     * @param waterPlantDto DTO containing the amount of water to pump (or duration to pump)
     * @return Response entity indicating success or failure of the watering trigger
     */
    @PostMapping("/water-plant")
    public ResponseEntity<String> triggerWatering(@RequestBody WaterPlantDto waterPlantDto) {
        // Trigger watering in IoT
        try {
            logger.info("Received request to water plant");
            mqttOutputChannel.sendToTopic(String.format("%f", waterPlantDto.waterAmount()));
        } catch (Exception e) {
            logger.error("Error sending water plant", e);
            return new ResponseEntity<>("Failed to trigger watering", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Persist watering data
        plantWateringService.waterPlant(waterPlantDto);
        return new ResponseEntity<>("Plant successfully watered!", HttpStatus.OK);
    }

    /**
     * Returns the last time the plants were watered and the amount watered
     */
    @GetMapping("/water-plant")
    public ResponseEntity<LastWaterResponse> getLastWatering() {
        LastWaterResponse lastWaterResponse = plantWateringService.getLastWatering();
        return new ResponseEntity<>(lastWaterResponse, HttpStatus.OK);
    }

    /**
     * Returns the latest amount of remaining water
     */
    @GetMapping("/remaining-water")
    public ResponseEntity<Float> getRemainingWater() {
        float remainingWater = waterTrackingService.getRemainingWater();
        return new ResponseEntity<>(remainingWater, HttpStatus.OK);
    }

    // TODO: Endpoint for water tracking
    // TODO: Need to find out how I can measure water tank water
}
