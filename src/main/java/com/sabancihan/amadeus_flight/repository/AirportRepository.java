package com.sabancihan.amadeus_flight.repository;

import com.sabancihan.amadeus_flight.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airport, UUID> {


    @Query("select a.id from Airport a")
    Set<UUID> getAirportIds();






}
