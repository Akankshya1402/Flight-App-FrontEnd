package com.flightapp.flightservice.service.impl;

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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceCircuitBreakerTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private FlightSummary createFallbackSample() {
        return new FlightSummary(
                0L,
                "NoFlights",
                "",
                "",
                "",
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                0
        );
    }
    
    @Test
    void testCircuitBreakerFallback_Search() {

        when(flightService.searchFlights(
                any(), anyInt(), anyInt(),
                anyString(), anyString(), any(), anyString()
        )).thenThrow(new RuntimeException("DB Down"));

        FlightSearchRequest request = new FlightSearchRequest(
                "A",
                "B",
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(3),
                false
        );

        List<FlightSummary> result = flightService.searchFlights(
                request,
                1,
                1,
                "O",
                "Economy",
                BigDecimal.valueOf(5000),
                "AirX"
        );

        assertNotNull(result);
        assertEquals(0, result.size());
    }


 


    @Test
    void testCircuitBreakerFallback_GetById() {
        // simulate failure
        when(flightRepository.findById(1L)).thenThrow(new RuntimeException("DB Down"));

        var result = flightService.getFlightById(1L);

        // fallback: Optional.empty
        assertTrue(result.isEmpty());
    }

    @Test
    void testCircuitBreakerFallback_GetSeats() {
        // simulate failure
        when(flightRepository.findById(1L)).thenThrow(new RuntimeException("DB Down"));

        Integer result = flightService.getAvailableSeats(1L);

        // fallback: 0 seats
        assertEquals(0, result);
    }
}
