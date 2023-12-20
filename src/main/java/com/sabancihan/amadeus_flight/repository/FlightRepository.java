package com.sabancihan.amadeus_flight.repository;

import com.sabancihan.amadeus_flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
