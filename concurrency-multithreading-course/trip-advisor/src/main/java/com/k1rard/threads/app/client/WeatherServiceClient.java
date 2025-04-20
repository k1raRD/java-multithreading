package com.k1rard.threads.app.client;

import com.k1rard.threads.app.dto.Transportation;
import com.k1rard.threads.app.dto.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class WeatherServiceClient {

    private final RestClient client;

    public Weather getWeather(String airportCode) {
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(Weather.class);
    }
}
