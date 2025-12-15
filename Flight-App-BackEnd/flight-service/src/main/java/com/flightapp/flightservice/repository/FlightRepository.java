package com.flightapp.flightservice.repository;

import com.flightapp.flightservice.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findByFromCityAndToCityAndDepartureTimeBetween(
            String fromCity,
            String toCity,
            LocalDateTime start,
            LocalDateTime end
    );
}
