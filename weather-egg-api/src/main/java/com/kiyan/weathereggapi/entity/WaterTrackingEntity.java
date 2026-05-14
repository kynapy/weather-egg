package com.kiyan.weathereggapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "WaterTracking")
public class WaterTrackingEntity {
    @Id
    private LocalDateTime timestamp;
    private float waterAmount;
}
