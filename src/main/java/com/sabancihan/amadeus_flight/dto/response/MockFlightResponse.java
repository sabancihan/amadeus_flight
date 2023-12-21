package com.sabancihan.amadeus_flight.dto.response;

import com.sabancihan.amadeus_flight.dto.request.FlightCreateRequest;
import com.sabancihan.amadeus_flight.model.Flight;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder

public class MockFlightResponse {

    List<FlightCreateRequest> flights;
}
