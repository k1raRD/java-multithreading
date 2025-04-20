package com.k1rard.threads.app.services;

import com.k1rard.threads.app.client.FlightReservationServiceClient;
import com.k1rard.threads.app.client.FlightSearchServiceClient;
import com.k1rard.threads.app.dto.Flight;
import com.k1rard.threads.app.dto.FlightReservationRequest;
import com.k1rard.threads.app.dto.FlightReservationResponse;
import com.k1rard.threads.app.dto.TripReservationRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripReservationService {

    private static final Logger log = LoggerFactory.getLogger(TripReservationService.class);
    private final FlightSearchServiceClient searchServiceClient;
    private final FlightReservationServiceClient reservationServiceClient;

    public FlightReservationResponse reserve(TripReservationRequest request) {
        List<Flight> flights = this.searchServiceClient.getFlights(request.departure(), request.arrival());
        Optional<Flight> bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));

        Flight flight = bestDeal.orElseThrow(() -> new IllegalStateException("No flights found"));
        FlightReservationRequest flightReservationRequest = new FlightReservationRequest(request.departure(),
                request.arrival(),
                flight.flightNumber(),
                request.date());

        return this.reservationServiceClient.reserve(flightReservationRequest);
    }
}
