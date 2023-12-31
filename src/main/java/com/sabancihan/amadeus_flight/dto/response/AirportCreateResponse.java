package com.sabancihan.amadeus_flight.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder

public class AirportCreateResponse {
    private UUID id;
    private String city;
}
