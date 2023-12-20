package com.sabancihan.amadeus_flight.dto.response;


import com.sabancihan.amadeus_flight.model.Airport;
import com.sabancihan.amadeus_flight.model.Flight;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FlightGetResponse {
    private LocalDateTime departureTime;


    private LocalDateTime arrivalTime;

    UUID departureAirport;

    UUID arrivalAirport;

    UUID id;

    BigDecimal price;


    public static FlightGetResponse fromFlight(Flight flight){
        return FlightGetResponse.builder()
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .departureAirport(flight.getDepartureAirport().getId())
                .arrivalAirport(flight.getArrivalAirport().getId())
                .price(flight.getPrice())
                .id(flight.getId())
                .build();
    }

}
