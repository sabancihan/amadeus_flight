package com.sabancihan.amadeus_flight.service.impl;

import com.sabancihan.amadeus_flight.dto.request.AirportCreateRequest;
import com.sabancihan.amadeus_flight.dto.response.AirportCreateResponse;
import com.sabancihan.amadeus_flight.dto.response.AirportGetResponse;
import com.sabancihan.amadeus_flight.model.Airport;
import com.sabancihan.amadeus_flight.repository.AirportRepository;
import com.sabancihan.amadeus_flight.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@RequiredArgsConstructor
@Service

public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void removeAirport(UUID id) {
        airportRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public AirportCreateResponse createAirport(AirportCreateRequest airportCreateRequest) {

          Airport airport =  airportRepository.save(Airport.builder()
                  .city(airportCreateRequest.getCity())
                  .build());

            return AirportCreateResponse.builder()
                    .id(airport.getId())
                    .city(airport.getCity())
                    .build();

    }

    @Override
    public AirportGetResponse getAirport(UUID id) {
        Airport airport =  airportRepository.findById(id).orElseThrow();
        return AirportGetResponse.fromAirport(airport);
    }

    @Override
    public List<AirportGetResponse> getAllAirports() {
        return airportRepository.findAll().stream().map(AirportGetResponse::fromAirport).toList();
    }

    @Override
    public Set<UUID> getAirportIds() {
        return airportRepository.getAirportIds();

    }
}
