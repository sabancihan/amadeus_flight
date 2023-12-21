package com.sabancihan.amadeus_flight.security.init;

import com.sabancihan.amadeus_flight.dto.request.AirportCreateRequest;
import com.sabancihan.amadeus_flight.dto.request.RegisterRequest;
import com.sabancihan.amadeus_flight.model.Airport;
import com.sabancihan.amadeus_flight.model.User;
import com.sabancihan.amadeus_flight.repository.AirportRepository;
import com.sabancihan.amadeus_flight.service.AirportService;
import com.sabancihan.amadeus_flight.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class TempEntityCreator implements CommandLineRunner {
    private final AuthService authService;

    private final AirportRepository airportRepository;

    private void createTempAirports() {



        List<Airport> airports = Stream.of("ISTANBUL","Ankara","Edirne","Antalya","Bursa").map(city -> Airport.builder().city(city).build()).toList();

        airportRepository.saveAll(airports);
    }


    @Override
    public void run(String... args) throws Exception {
        authService.registerUser(
               RegisterRequest.builder()
                        .username("admin")
                        .password("trial")
                        .role(User.Role.ADMIN)

                        .build()
        );

        authService.registerUser(
                RegisterRequest.builder()
                        .username("user")
                        .password("trial1")
                        .role(User.Role.USER)
                        .build()
        );

        createTempAirports();




    }
}
