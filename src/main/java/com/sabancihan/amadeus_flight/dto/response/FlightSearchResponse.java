package com.sabancihan.amadeus_flight.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder

public class FlightSearchResponse {

    @NotNull
    List<FlightSearchResult> departures;

    List<FlightSearchResult> returns;
}
