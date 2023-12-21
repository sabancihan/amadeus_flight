package com.sabancihan.amadeus_flight.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportCreateRequest {
    @NotNull
    private String city;
}
