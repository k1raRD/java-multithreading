package com.k1rard.threads.app.controllers;

import com.k1rard.threads.app.dto.FlightReservationResponse;
import com.k1rard.threads.app.dto.TripPlan;
import com.k1rard.threads.app.dto.TripReservationRequest;
import com.k1rard.threads.app.services.TripPlanService;
import com.k1rard.threads.app.services.TripReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")
@RequiredArgsConstructor
public class TripController {
    private final TripPlanService planService;
    private final TripReservationService reservationService;

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode) {
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFLight(@RequestBody TripReservationRequest request) {
        return this.reservationService.reserve(request);
    }
}
