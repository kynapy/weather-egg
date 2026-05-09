package com.kiyan.weathereggapi.service;

import com.kiyan.weathereggapi.entity.TemperatureEntity;
import com.kiyan.weathereggapi.repository.TemperatureRepository;
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
public class TemperatureServiceTest {
    @Mock
    private TemperatureRepository temperatureRepository;

    @InjectMocks
    private TemperatureService temperatureService;

    @Test
    void getLatestWeather_ShouldReturnValidaData() {
        TemperatureEntity fakeWeather = new TemperatureEntity();
        fakeWeather.setTimestamp(LocalDateTime.now());
        fakeWeather.setTemperature(25.5f);
        fakeWeather.setHumidity(60.5f);

        when(temperatureRepository.findFirstByOrderByTimestampDesc())
                .thenReturn(fakeWeather);

        TemperatureEntity result = temperatureService.getLatestWeather();
        assertNotNull(result);
        assertEquals(25.5f, result.getTemperature());
        assertEquals(60.5f, result.getHumidity());

        verify(temperatureRepository, times(1)).findFirstByOrderByTimestampDesc();
    }
}
