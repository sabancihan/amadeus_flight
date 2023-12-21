package com.sabancihan.amadeus_flight.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface FlightSearchResult {

    UUID getId();
    BigDecimal getPrice();

    UUID getDepartureId();

    UUID getArrivalId();

    LocalDateTime getDepartureTime();

    LocalDateTime getArrivalTime();

    String getDepartureCity();

    String getArrivalCity();

}
