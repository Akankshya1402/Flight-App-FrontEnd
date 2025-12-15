package com.flightapp.flightservice.controller;

import com.flightapp.flightservice.dto.AddInventoryRequest;
import com.flightapp.flightservice.dto.FlightSearchRequest;
import com.flightapp.flightservice.dto.FlightSummary;
import com.flightapp.flightservice.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private static final Logger log = LoggerFactory.getLogger(FlightController.class);

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public FlightSummary addFlight(@RequestBody AddInventoryRequest request) {
        return flightService.addInventory(request);
    }

    @PostMapping("/search")
    public List<FlightSummary> searchFlights(
            @RequestBody FlightSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "departureTime") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String airlineName
    ) {
        return flightService.searchFlights(
                request, page, size, sortBy, sortDir, maxPrice, airlineName
        );
    }
    @GetMapping("/{id}")
    public FlightSummary getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
    }


    @GetMapping("/all")
    public List<FlightSummary> getAllFlights(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return flightService.getAllFlights(page, size);
    }

    @GetMapping("/{id}/seats")
    public Integer getAvailableSeats(@PathVariable Long id) {
        return flightService.getAvailableSeats(id);
    }
}

