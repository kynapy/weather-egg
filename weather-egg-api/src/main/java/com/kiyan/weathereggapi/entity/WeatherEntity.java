package com.kiyan.weathereggapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "Weather")
public class WeatherEntity {
    @Id
    private OffsetDateTime timestamp;
    private float temperature;
    private float humidity;
}
