package com.flightapp.flightservice.service.impl;

import com.flightapp.flightservice.dto.AddInventoryRequest;
import com.flightapp.flightservice.dto.FlightSearchRequest;
import com.flightapp.flightservice.dto.FlightSummary;
import com.flightapp.flightservice.entity.Flight;
import com.flightapp.flightservice.repository.FlightRepository;
import com.flightapp.flightservice.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Flight createFlight(Long id) {
        Flight f = new Flight();
        f.setId(id);
        f.setAirlineName("AirX");
        f.setLogoUrl("logo.png");
        f.setFromCity("A");
        f.setToCity("B");
        f.setDepartureTime(LocalDateTime.parse("2025-01-10T10:00:00"));
        f.setArrivalTime(LocalDateTime.parse("2025-01-10T12:00:00"));
        f.setRoundTrip(false);
        f.setPriceOneWay(BigDecimal.valueOf(2000));
        f.setPriceRoundTrip(BigDecimal.valueOf(4000));
        f.setAvailableSeats(50);
        return f;
    }

    private FlightSummary createSummary(Long id) {
        return new FlightSummary(
                id,
                "AirX",
                "logo.png",
                "A",
                "B",
                LocalDateTime.parse("2025-01-10T10:00:00"),
                LocalDateTime.parse("2025-01-10T12:00:00"),
                false,
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(4000),
                50
        );
    }

    @Test
    void testGetFlightById_Found() {
        Flight f = createFlight(1L);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(f));

        Optional<FlightSummary> result = flightService.getFlightById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
    }

    @Test
    void testGetFlightById_NotFound() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<FlightSummary> result = flightService.getFlightById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testGetAvailableSeats() {
        Flight f = createFlight(1L);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(f));

        Integer seats = flightService.getAvailableSeats(1L);

        assertEquals(50, seats);
    }
    @Test
    void testAddInventory() {

        LocalDateTime d1 = LocalDateTime.parse("2025-01-10T10:00:00");
        LocalDateTime d2 = LocalDateTime.parse("2025-01-10T12:00:00");

        AddInventoryRequest req = new AddInventoryRequest(
                "AX",                 // airlineCode
                "AirX",               // airlineName
                "logo.png",           // logoUrl
                "A",                  // fromCity
                "B",                  // toCity
                d1,                   // departureTime
                d2,                   // arrivalTime
                false,                // roundTrip
                50,                   // totalSeats
                BigDecimal.valueOf(2000), // priceOneWay
                BigDecimal.valueOf(4000)  // priceRoundTrip
        );

        Flight f = createFlight(1L);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(f));
        when(flightRepository.save(any())).thenReturn(f);

        FlightSummary result = flightService.addInventory(req);

        assertEquals(1L, result.id());
        assertEquals(50, result.availableSeats());
    }


   
}

