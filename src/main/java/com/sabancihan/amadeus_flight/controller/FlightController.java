package com.sabancihan.amadeus_flight.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sabancihan.amadeus_flight.dto.request.FlightCreateRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightSearchRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightUpdateRequest;
import com.sabancihan.amadeus_flight.dto.response.AirportGetResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightCreateResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightGetResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightSearchResponse;
import com.sabancihan.amadeus_flight.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;



    @DeleteMapping("{id}")
    public void deleteFlightById(@PathVariable UUID id) {
         flightService.removeFlight(id);
    }


    @PatchMapping("{id}")
    public FlightGetResponse updateFlightById(@PathVariable UUID id, @RequestBody @Valid FlightUpdateRequest flightUpdateRequest) throws JsonMappingException {
        return flightService.updateFlight(id, flightUpdateRequest);
    }

    @GetMapping("{id}")
    public FlightGetResponse getFlightById(@PathVariable UUID id) {
        return flightService.getFlight(id);
    }

    @PostMapping
    public FlightCreateResponse createFlight(@Valid @RequestBody FlightCreateRequest flightCreateRequest) throws BadRequestException {
        return flightService.createFlight(flightCreateRequest);
    }

    @GetMapping
    public List<FlightGetResponse> getAllFlights() {
        return flightService.getAllFlights();
    }


    @PostMapping("/search")
    public FlightSearchResponse searchFlights(@Valid @RequestBody FlightSearchRequest flightSearchRequest) throws BadRequestException {
        return flightService.searchFlights(flightSearchRequest);
    }
}
