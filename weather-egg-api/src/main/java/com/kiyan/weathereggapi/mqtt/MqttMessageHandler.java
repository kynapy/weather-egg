package com.kiyan.weathereggapi.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiyan.weathereggapi.dto.TemperatureDto;
import com.kiyan.weathereggapi.service.TemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageHandler {
    private final ObjectMapper objectMapper;
    private final TemperatureService temperatureService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttMessageHandler.class);

    public MqttMessageHandler(ObjectMapper objectMapper, TemperatureService temperatureService) {
        this.objectMapper = objectMapper;
        this.temperatureService = temperatureService;
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handle(Message<String> message) {
        String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
        String payload = message.getPayload();

        try {
            LOGGER.info("Received on topic [{}]: {}", topic, payload);
            TemperatureDto temperatureDto = objectMapper.readValue(payload, TemperatureDto.class);
            temperatureService.uploadTemperature(temperatureDto);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
