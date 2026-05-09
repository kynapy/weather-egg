package com.kiyan.weathereggapi.service;

import com.kiyan.weathereggapi.entity.WeatherEntity;
import com.kiyan.weathereggapi.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void getLatestWeather_ShouldReturnValidaData() {
        WeatherEntity fakeWeather = new WeatherEntity();
        fakeWeather.setTimestamp(LocalDateTime.now());
        fakeWeather.setTemperature(25.5f);
        fakeWeather.setHumidity(60.5f);

        when(weatherRepository.findFirstByOrderByTimestampDesc())
                .thenReturn(fakeWeather);

        WeatherEntity result = weatherService.getLatestWeather();
        assertNotNull(result);
        assertEquals(25.5f, result.getTemperature());
        assertEquals(60.5f, result.getHumidity());

        verify(weatherRepository, times(1)).findFirstByOrderByTimestampDesc();
    }
}
