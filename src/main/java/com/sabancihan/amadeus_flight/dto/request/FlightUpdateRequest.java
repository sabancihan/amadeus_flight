package com.sabancihan.amadeus_flight.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightUpdateRequest {
    LocalDateTime departureTime; //utc time

    LocalDateTime arrivalTime; //utc time

    UUID departureAirportId;

    UUID arrivalAirportId;

    @Min(0)
    BigDecimal price;

}
