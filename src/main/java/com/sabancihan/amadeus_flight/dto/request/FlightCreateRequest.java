package com.sabancihan.amadeus_flight.dto.request;

import com.sabancihan.amadeus_flight.model.Airport;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FlightCreateRequest {

    @NotNull
    LocalDateTime departureTime; //utc time

    @NotNull
    LocalDateTime arrivalTime; //utc time

    @NotNull
    UUID departureAirportId;

    @NotNull
    UUID arrivalAirportId;

    @NotNull
    @Min(0)
    BigDecimal price;


    @AssertTrue

    public boolean isValid() {
        return arrivalTime.isAfter(departureTime) && !arrivalAirportId.equals(departureAirportId);
    }



}
