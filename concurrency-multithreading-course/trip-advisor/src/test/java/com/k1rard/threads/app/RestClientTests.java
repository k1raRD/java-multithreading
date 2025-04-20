package com.k1rard.threads.app;

import com.k1rard.threads.app.dto.Acommodation;
import com.k1rard.threads.app.dto.FlightReservationRequest;
import com.k1rard.threads.app.dto.FlightReservationResponse;
import com.k1rard.threads.app.dto.Weather;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

/**
 * This is a simple demo to play with RestClient
 * Not spring unit tests / integration tests
 */
public class RestClientTests {

    private static final Logger log = LoggerFactory.getLogger(RestClientTests.class);

    @Test
    void simpleGet() {

        RestClient restClient = RestClient.create();
        Weather response = restClient.get()
                .uri("http://localhost:7070/sec02/weather/LAS")
                .retrieve()
                .body(Weather.class);
        log.info("response: {}", response);
    }

    @Test
    void baseUrl() {

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:7070/sec02/weather/")
                .build();

        Weather response = restClient.get()
                .uri("{airportCode}","LAS")
                .retrieve()
                .body(Weather.class);
        log.info("response: {}", response);
    }

    @Test
    void listResponse() {

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:7070/sec02/accommodations/")
                .build();

        var response = restClient.get()
                .uri("{airportCode}","LAS")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Acommodation>>() {
                });
        log.info("response: {}", response);
    }

    @Test
    void postRequest() {

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:7070/sec03/flight/reserve/")
                .build();

        var request = new FlightReservationRequest("ATL", "LAS", "UA789", LocalDate.now());
        var response = restClient.post()
                .body(request)
                .retrieve()
                .body(FlightReservationResponse.class);
        log.info("response: {}", response);
    }
}
