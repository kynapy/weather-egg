package com.kiyan.weathereggapi.dto;

import java.time.LocalDateTime;

public record LastWaterResponse (
        float waterAmount,
        LocalDateTime lastWaterTime
) {
}
