package com.flightapp.flightservice.controller;

import com.flightapp.flightservice.dto.AddInventoryRequest;
import com.flightapp.flightservice.dto.FlightSearchRequest;
import com.flightapp.flightservice.dto.FlightSummary;
import com.flightapp.flightservice.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FlightControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    private FlightSummary createSample(long id) {
        LocalDateTime d1 = LocalDateTime.parse("2025-01-10T10:00:00");
        LocalDateTime d2 = LocalDateTime.parse("2025-01-10T12:00:00");

        return new FlightSummary(
                id,
                "AirX",
                "logo.png",
                "A",
                "B",
                d1,
                d2,
                false,
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(4000),
                50
        );
    }

    @Test
    void testGetFlightById() throws Exception {

        FlightSummary summary = createSample(1);

        when(flightService.getFlightById(1L)).thenReturn(Optional.of(summary));

        mockMvc.perform(get("/flight/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.airlineName").value("AirX"));
    }

    @Test
    void testSearchFlights() throws Exception {

        FlightSummary f1 = createSample(1);

        when(flightService.searchFlights(
                any(), anyInt(), anyInt(), anyString(), anyString(), any(), anyString()
        )).thenReturn(List.of(f1));

        mockMvc.perform(post("/flight/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"source\":\"A\", \"destination\":\"B\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }


    @Test
    void testGetAvailableSeats() throws Exception {

        when(flightService.getAvailableSeats(1L)).thenReturn(25);

        mockMvc.perform(get("/flight/1/seats"))
                .andExpect(status().isOk())
                .andExpect(content().string("25"));
    }

    @Test
    void testAddInventory() throws Exception {

        FlightSummary summary = createSample(2);

        when(flightService.addInventory(any(AddInventoryRequest.class)))
                .thenReturn(summary);

        mockMvc.perform(post("/flight/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"flightId\": 2, \"seats\": 10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }
}
