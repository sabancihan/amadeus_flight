package com.sabancihan.amadeus_flight.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabancihan.amadeus_flight.dto.request.FlightCreateRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightSearchRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightUpdateRequest;
import com.sabancihan.amadeus_flight.dto.response.FlightCreateResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightGetResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightSearchResponse;
import com.sabancihan.amadeus_flight.dto.response.FlightSearchResult;
import com.sabancihan.amadeus_flight.exception.FlightNotFoundException;
import com.sabancihan.amadeus_flight.model.Flight;
import com.sabancihan.amadeus_flight.repository.FlightRepository;
import com.sabancihan.amadeus_flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

//left here
@Service
@RequiredArgsConstructor

public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    private final ObjectMapper objectMapper;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void removeFlight(UUID id) {
        flightRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public FlightCreateResponse createFlight(FlightCreateRequest flightCreateRequest) throws BadRequestException {
        if (flightCreateRequest.getArrivalAirportId().equals(flightCreateRequest.getDepartureAirportId())) {
            throw new BadRequestException("Arrival and departure airports cannot be the same");
        }

        Flight flight = flightRepository.save(Flight.builder()
                .arrivalAirportId(flightCreateRequest.getArrivalAirportId())
                .departureAirportId(flightCreateRequest.getDepartureAirportId())
                .price(flightCreateRequest.getPrice())
                .departureTime(flightCreateRequest.getDepartureTime())
                .arrivalTime(flightCreateRequest.getArrivalTime())
                .build());

        return FlightCreateResponse.fromFlight(flight);


    }

    @Override
    public FlightGetResponse getFlight(UUID id) {
        Flight flight =  flightRepository.findById(id).orElseThrow();
        return FlightGetResponse.fromFlight(flight);


    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public FlightGetResponse updateFlight(UUID id, FlightUpdateRequest flightUpdateRequest) throws JsonMappingException {

        Flight flight = flightRepository.findById(id).orElseThrow();
        objectMapper.updateValue(flight,flightUpdateRequest);


        return FlightGetResponse.fromFlight(flightRepository.save(flight));



    }

    @Override
    public List<FlightGetResponse> getAllFlights() {
        return flightRepository.findAll().stream().map(FlightGetResponse::fromFlight).toList();
    }

    @Override
    public FlightSearchResponse searchFlights(FlightSearchRequest flightSearchRequest) throws BadRequestException, FlightNotFoundException {
        String departureAirport = flightSearchRequest.getDepartureAirport();
        String arrivalAirport = flightSearchRequest.getArrivalAirport();




        if (flightSearchRequest.getReturnDate() == null) {

            List<FlightSearchResult> flights = flightRepository.findOneWayAllByDepartureAirportAndArrivalAirportAndDepartureTime(departureAirport, arrivalAirport, flightSearchRequest.getDepartureDate(), flightSearchRequest.getDepartureDate().plusDays(1));

            if (flights == null) {
                throw new FlightNotFoundException("No flights found");
            }

            return FlightSearchResponse.builder()
                    .departures(flights)
                    .build();
        }
        else {


            Map<String,List<FlightSearchResult>> flightSearchResults =
                   flightRepository.findTwoWayAllByDepartureAirportAndArrivalAirportAndDepartureTime(departureAirport,arrivalAirport, flightSearchRequest.getDepartureDate(), flightSearchRequest.getDepartureDate().plusDays(1), flightSearchRequest.getReturnDate(), flightSearchRequest.getReturnDate().plusDays(1)).stream().collect(groupingBy(FlightSearchResult::getDepartureCity));




            List<FlightSearchResult> departureFlights = flightSearchResults.get(departureAirport);
            List<FlightSearchResult> arrivalFlights = flightSearchResults.get(arrivalAirport);


            if (departureFlights == null || arrivalFlights == null) {
                throw new FlightNotFoundException("No flights found");
            }


            return FlightSearchResponse.builder()
                    .departures(departureFlights)
                    .returns(arrivalFlights)
                    .build();

        }
    }
}
