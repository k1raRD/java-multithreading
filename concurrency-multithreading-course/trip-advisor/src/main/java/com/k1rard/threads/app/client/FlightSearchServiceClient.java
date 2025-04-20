package com.k1rard.threads.app.client;

import com.k1rard.threads.app.dto.Flight;
import com.k1rard.threads.app.dto.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class FlightSearchServiceClient {

    private final RestClient client;

    public List<Flight> getFlights(String departure, String arrival) {
        return this.client.get()
                .uri("/{departure}/{arrival}", departure, arrival)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Flight>>() {
                });
    }
}
