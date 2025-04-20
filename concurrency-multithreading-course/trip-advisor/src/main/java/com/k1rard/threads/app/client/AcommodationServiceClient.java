package com.k1rard.threads.app.client;

import com.k1rard.threads.app.dto.Acommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class AcommodationServiceClient {

    private final RestClient client;

    public List<Acommodation> getAcommodations(String airportCode) {
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Acommodation>>() {
                });
    }
}
