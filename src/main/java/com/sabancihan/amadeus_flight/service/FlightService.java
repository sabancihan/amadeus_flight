package com.sabancihan.amadeus_flight.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sabancihan.amadeus_flight.dto.request.FlightCreateRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightSearchRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightUpdateRequest;
import com.sabancihan.amadeus_flight.dto.response.FlightCreateResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightGetResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightSearchResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface FlightService {

    void removeFlight(UUID id);

    FlightCreateResponse createFlight(FlightCreateRequest flightCreateRequest) throws BadRequestException;

    FlightGetResponse getFlight(UUID id);

    FlightGetResponse updateFlight(UUID id, FlightUpdateRequest flightUpdateRequest) throws JsonMappingException;

    List<FlightGetResponse> getAllFlights();

    FlightSearchResponse searchFlights(FlightSearchRequest flightSearchRequest) throws BadRequestException;




}
