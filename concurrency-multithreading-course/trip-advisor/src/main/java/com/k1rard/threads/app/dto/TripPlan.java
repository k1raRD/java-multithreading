package com.k1rard.threads.app.dto;

import java.util.List;

public record TripPlan(String airportCode,
                       List<Event> events,
                       Weather weather,
                       List<Acommodation> acommodations,
                       Transportation transportation,
                       LocalRecommendations localRecommendation) {
}
