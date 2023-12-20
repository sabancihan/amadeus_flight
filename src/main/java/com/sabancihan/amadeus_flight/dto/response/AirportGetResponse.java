package com.sabancihan.amadeus_flight.dto.response;

import com.sabancihan.amadeus_flight.model.Airport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder

public class AirportGetResponse {
    private UUID id;
    private String city;

    public static AirportGetResponse  fromAirport(Airport airport){
        return AirportGetResponse.builder()
                .city(airport.getCity())
                .id(airport.getId())
                .build();
    }

}
