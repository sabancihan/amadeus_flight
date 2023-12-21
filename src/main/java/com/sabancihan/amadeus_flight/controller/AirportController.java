package com.sabancihan.amadeus_flight.controller;


import com.sabancihan.amadeus_flight.dto.request.AirportCreateRequest;
import com.sabancihan.amadeus_flight.dto.response.AirportCreateResponse;
import com.sabancihan.amadeus_flight.dto.response.AirportGetResponse;
import com.sabancihan.amadeus_flight.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @Operation(summary = "Get an Airport by uuid")

    @GetMapping("{id}")
    public AirportGetResponse getAirportById(@Parameter(description = "uuid of airport to be searched")  @PathVariable UUID id) {
        return airportService.getAirport(id);
    }


    @Operation(summary = "Delete an Airport by uuid")

    @DeleteMapping("{id}")
    public void deleteAirportById(@Parameter(description = "uuid of airport to be deleted") @PathVariable UUID id) {
        airportService.removeAirport(id);
    }


    @Operation(summary = "Create an Airport")

    @PostMapping
    public AirportCreateResponse createAirport(@RequestBody @Valid AirportCreateRequest airportCreateRequest) {
        return airportService.createAirport(airportCreateRequest);
    }


    @Operation(summary = "Get all Airports")

    @GetMapping
    public List<AirportGetResponse> getAllAirports() {
        return airportService.getAllAirports();
    }
}
