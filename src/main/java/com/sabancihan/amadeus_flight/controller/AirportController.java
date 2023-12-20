package com.sabancihan.amadeus_flight.controller;


import com.sabancihan.amadeus_flight.dto.request.AirportCreateRequest;
import com.sabancihan.amadeus_flight.dto.response.AirportCreateResponse;
import com.sabancihan.amadeus_flight.dto.response.AirportGetResponse;
import com.sabancihan.amadeus_flight.service.AirportService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("{id}")
    public AirportGetResponse getAirportById(@PathVariable UUID id) {
        return airportService.getAirport(id);
    }

    @DeleteMapping("{id}")
    public void deleteAirportById(@PathVariable UUID id) {
        airportService.removeAirport(id);
    }

    @PostMapping
    public AirportCreateResponse createAirport(@RequestBody @Valid AirportCreateRequest airportCreateRequest) {
        return airportService.createAirport(airportCreateRequest);
    }

    @GetMapping
    public List<AirportGetResponse> getAllAirports() {
        return airportService.getAllAirports();
    }
}
