package com.flightapp.flightservice.service.impl;

import com.flightapp.flightservice.dto.AddInventoryRequest;
import com.flightapp.flightservice.dto.FlightSearchRequest;
import com.flightapp.flightservice.dto.FlightSummary;
import com.flightapp.flightservice.entity.Flight;
import com.flightapp.flightservice.exception.FlightNotFoundException;
import com.flightapp.flightservice.mapper.FlightMapper;
import com.flightapp.flightservice.repository.FlightRepository;
import com.flightapp.flightservice.service.FlightService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FlightServiceImpl implements FlightService {

    private static final Logger log =
            LoggerFactory.getLogger(FlightServiceImpl.class);

    private static final String FLIGHT_SEARCH_CB = "flightSearchCB";

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /* ================= ADD INVENTORY ================= */

    @Override
    public FlightSummary addInventory(AddInventoryRequest request) {

        log.info("Adding flight {} → {} ({})",
                request.fromCity(),
                request.toCity(),
                request.airlineName());

        Flight entity = FlightMapper.toEntity(request);
        Flight saved = flightRepository.save(entity);

        return FlightMapper.toSummary(saved);
    }

    /* ================= SEARCH FLIGHTS ================= */

    @Override
    @Cacheable(
            cacheNames = "flightSearch",
            key = "#request.fromCity() + '-' + #request.toCity() + '-' + " +
                  "#request.departureFrom() + '-' + #request.departureTo() + '-' + " +
                  "#page + '-' + #size + '-' + #sortBy + '-' + #sortDir + '-' + " +
                  "#maxPrice + '-' + #airlineName"
    )
    @CircuitBreaker(
            name = FLIGHT_SEARCH_CB,
            fallbackMethod = "searchFlightsFallback"
    )
    public List<FlightSummary> searchFlights(
            FlightSearchRequest request,
            int page,
            int size,
            String sortBy,
            String sortDir,
            BigDecimal maxPrice,
            String airlineName
    ) {

        log.info("Searching flights {} → {} | {} to {}",
                request.fromCity(),
                request.toCity(),
                request.departureFrom(),
                request.departureTo());

        List<Flight> flights =
                flightRepository.findByFromCityAndToCityAndDepartureTimeBetween(
                        request.fromCity(),
                        request.toCity(),
                        request.departureFrom(),
                        request.departureTo()
                );

        List<FlightSummary> filtered =
                flights.stream()
                        .filter(f ->
                                airlineName == null ||
                                f.getAirlineName().equalsIgnoreCase(airlineName))
                        .filter(f ->
                                maxPrice == null ||
                                (f.getPriceOneWay() != null &&
                                 f.getPriceOneWay().compareTo(maxPrice) <= 0))
                        .map(FlightMapper::toSummary)
                        .sorted(getComparator(sortBy, sortDir))
                        .collect(Collectors.toList());

        int start = page * size;
        int end = Math.min(start + size, filtered.size());

        if (start >= filtered.size()) {
            return Collections.emptyList();
        }

        return filtered.subList(start, end);
    }

    /* ================= CIRCUIT BREAKER FALLBACK ================= */

    public List<FlightSummary> searchFlightsFallback(
            FlightSearchRequest request,
            int page,
            int size,
            String sortBy,
            String sortDir,
            BigDecimal maxPrice,
            String airlineName,
            Throwable ex
    ) {
        log.error("Flight search fallback triggered", ex);
        return Collections.emptyList();
    }

    /* ================= SORTING ================= */

    private Comparator<FlightSummary> getComparator(
            String sortBy,
            String sortDir
    ) {

        Comparator<FlightSummary> comparator;

        switch (sortBy) {
            case "price":
            case "priceOneWay":
                comparator = Comparator.comparing(
                        FlightSummary::priceOneWay,
                        Comparator.nullsLast(BigDecimal::compareTo)
                );
                break;

            case "airlineName":
                comparator = Comparator.comparing(
                        FlightSummary::airlineName,
                        Comparator.nullsLast(String::compareToIgnoreCase)
                );
                break;

            default:
                comparator = Comparator.comparing(
                        FlightSummary::departureTime
                );
        }

        return "desc".equalsIgnoreCase(sortDir)
                ? comparator.reversed()
                : comparator;
    }

    /* ================= GET BY ID ================= */

    @Override
    public Optional<FlightSummary> getFlightById(Long id) {

        log.info("Fetching flight id={}", id);

        return flightRepository.findById(id)
                .map(FlightMapper::toSummary);
    }

    /* ================= GET ALL ================= */

    @Override
    public List<FlightSummary> getAllFlights(int page, int size) {

        Iterable<Flight> iterable = flightRepository.findAll();

        List<FlightSummary> flights =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(FlightMapper::toSummary)
                        .sorted(Comparator.comparing(
                                FlightSummary::departureTime))
                        .collect(Collectors.toList());

        int start = page * size;
        int end = Math.min(start + size, flights.size());

        if (start >= flights.size()) {
            return Collections.emptyList();
        }

        return flights.subList(start, end);
    }

    /* ================= AVAILABLE SEATS ================= */

    @Override
    public int getAvailableSeats(Long id) {

        return flightRepository.findById(id)
                .map(Flight::getAvailableSeats)
                .orElseThrow(() ->
                        new FlightNotFoundException(
                                "Flight not found with id: " + id));
    }
}
