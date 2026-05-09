package com.kiyan.weathereggapi.dto;

public record BusArrivalQueryDto(
        int busStopCode,
        int serviceNumber
) {
}
