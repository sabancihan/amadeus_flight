package com.sabancihan.amadeus_flight.dto.request;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchRequest {

    @NotNull
    String departureAirport;

    @NotNull
    String arrivalAirport;

    @NotNull
    LocalDate departureDate;

    LocalDate returnDate;


}
