package com.sabancihan.amadeus_flight.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public interface FlightSearchResult {

    UUID getId();
    BigDecimal getPrice();

    UUID getDepartureId();

    UUID getArrivalId();

}
