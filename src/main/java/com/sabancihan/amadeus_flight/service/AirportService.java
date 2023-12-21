package com.sabancihan.amadeus_flight.service;

import com.sabancihan.amadeus_flight.dto.request.AirportCreateRequest;
import com.sabancihan.amadeus_flight.dto.response.AirportCreateResponse;
import com.sabancihan.amadeus_flight.dto.response.AirportGetResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AirportService {
    void removeAirport(UUID id);

    AirportCreateResponse createAirport(AirportCreateRequest airportCreateRequest);

    AirportGetResponse getAirport(UUID id);

    List<AirportGetResponse> getAllAirports();


    Set<UUID> getAirportIds();




}
