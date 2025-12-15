package com.flightapp.flightservice.service;

import com.flightapp.flightservice.dto.AddInventoryRequest;
import com.flightapp.flightservice.dto.FlightSearchRequest;
import com.flightapp.flightservice.dto.FlightSummary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FlightService {

    FlightSummary addInventory(AddInventoryRequest request);

    List<FlightSummary> searchFlights(
            FlightSearchRequest request,
            int page,
            int size,
            String sortBy,
            String sortDir,
            BigDecimal maxPrice,
            String airlineName
    );

    Optional<FlightSummary> getFlightById(Long id);

    List<FlightSummary> getAllFlights(int page, int size);

    int getAvailableSeats(Long id);
}
