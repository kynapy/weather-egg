package com.kiyan.weathereggapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Temperature")
public class Temperature {
    @Id
    @GeneratedValue
    private int id;
    private float temperature;

    public float getTemperature() {
        return this.temperature;
    }
}
