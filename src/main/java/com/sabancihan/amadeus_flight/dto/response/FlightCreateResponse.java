package com.sabancihan.amadeus_flight.dto.response;

import com.sabancihan.amadeus_flight.model.Flight;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder

public class FlightCreateResponse {

    private LocalDateTime departureTime;


    private LocalDateTime arrivalTime;

    UUID departureAirport;

    UUID arrivalAirport;

    UUID id;


    BigDecimal price;


    public static FlightCreateResponse fromFlight(Flight flight){
        return FlightCreateResponse.builder()
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .departureAirport(flight.getDepartureAirportId())
                .arrivalAirport(flight.getArrivalAirportId())
                .price(flight.getPrice())
                .id(flight.getId())
                .build();
    }
}
