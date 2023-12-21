package com.sabancihan.amadeus_flight.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabancihan.amadeus_flight.dto.request.FlightCreateRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightSearchRequest;
import com.sabancihan.amadeus_flight.dto.request.FlightUpdateRequest;
import com.sabancihan.amadeus_flight.dto.response.*;
import com.sabancihan.amadeus_flight.exception.FlightNotFoundException;
import com.sabancihan.amadeus_flight.model.Flight;
import com.sabancihan.amadeus_flight.repository.FlightRepository;
import com.sabancihan.amadeus_flight.service.AirportService;
import com.sabancihan.amadeus_flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

//left here
@Service
@RequiredArgsConstructor

public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    private final ObjectMapper objectMapper;

    private final AirportService airportService;

    @Value("${amadeus.mock.api}")
    private String mockapi;


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

        Flight flight = flightRepository.save(flightCreateRequest.toFlight());

        return FlightCreateResponse.fromFlight(flight);


    }

    @Scheduled(cron = "0 0 0 * * *", zone = "UTC")
    @EventListener(ApplicationReadyEvent.class)
    public void addNewFlights() {
        RestClient restClient = RestClient.create();
        FlightCreateRequest[] mockFlightResponse = restClient.get()
                .uri(mockapi)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(FlightCreateRequest[].class);

        if (mockFlightResponse == null) {
            return;
        }

        List<Flight> flights = Stream.of(mockFlightResponse).map(FlightCreateRequest::toFlight).toList();

        //because of the mock api airports id's may not be valid and cannot be synced also arrival and departure times may not be valid
        Set<UUID> airportIds = airportService.getAirportIds();

        if (airportIds.size() < 2) {
            return; //if there are less than 2 airports in the database there is no point in adding flights as departure and arrival airports cannot be the same
        }


        flightRepository.saveAll(flights.stream().filter(flight -> airportIds.contains(flight.getArrivalAirportId()) && airportIds.contains(flight.getDepartureAirportId()) && flight.getArrivalTime().isAfter(flight.getDepartureTime())).toList());
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
