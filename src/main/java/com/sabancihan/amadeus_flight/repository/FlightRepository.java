package com.sabancihan.amadeus_flight.repository;

import com.sabancihan.amadeus_flight.dto.response.FlightSearchResult;
import com.sabancihan.amadeus_flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {



    @Query(value = "SELECT f.id as id,price,d.id as departureId,a.id as arrivalId,f.departure_time as departureTime,f.arrival_time as arrivalTime,d.city as departureCity,a.city as arrivalCity FROM Flight f INNER JOIN airport d ON f.departure_airport_id = d.id INNER JOIN airport a ON f.arrival_airport_id = a.id WHERE d.city = ?1 AND a.city = ?2 AND f.departure_time >= ?3 AND f.departure_time < ?4 ORDER BY price", nativeQuery = true)
    List<FlightSearchResult> findOneWayAllByDepartureAirportAndArrivalAirportAndDepartureTime(String departureAirport, String arrivalAirport, LocalDate departureTimeStart, LocalDate departureTimeEnd);


    @Query(value = "SELECT f.id as id,price,d.id as departureId,a.id as arrivalId,f.departure_time as departureTime,f.arrival_time as arrivalTime,d.city as departureCity,a.city as arrivalCity  FROM Flight f INNER JOIN airport d ON f.departure_airport_id = d.id INNER JOIN airport a ON f.arrival_airport_id = a.id WHERE (d.city = ?1 AND a.city = ?2 AND f.departure_time >= ?3 AND f.departure_time < ?4) OR (d.city = ?2 AND a.city = ?1 AND f.departure_time >= ?5 AND f.departure_time < ?6) ORDER BY departure_time "
            , nativeQuery = true)
    List<FlightSearchResult> findTwoWayAllByDepartureAirportAndArrivalAirportAndDepartureTime(String departureAirport, String arrivalAirport, LocalDate departureTimeStart, LocalDate departureTimeEnd, LocalDate returnTimeStart, LocalDate returnTimeEnd);



}
