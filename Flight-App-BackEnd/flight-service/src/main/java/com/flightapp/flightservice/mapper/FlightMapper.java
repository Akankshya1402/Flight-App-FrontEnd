package com.flightapp.flightservice.mapper;

import com.flightapp.flightservice.dto.AddInventoryRequest;
import com.flightapp.flightservice.dto.FlightSummary;
import com.flightapp.flightservice.entity.Flight;

import java.math.BigDecimal;

public final class FlightMapper {

    private FlightMapper() {}

    public static Flight toEntity(AddInventoryRequest r) {
        return new Flight.Builder()
                .airlineCode(r.airlineCode())
                .airlineName(r.airlineName())
                .logoUrl(r.logoUrl())
                .fromCity(r.fromCity())
                .toCity(r.toCity())
                .departureTime(r.departureTime())
                .arrivalTime(r.arrivalTime())
                .roundTrip(r.roundTrip())
                .availableSeats(r.totalSeats())
                .priceOneWay(r.priceOneWay())
                .priceRoundTrip(
                        r.priceRoundTrip() != null
                                ? r.priceRoundTrip()
                                : r.priceOneWay().multiply(BigDecimal.valueOf(2))
                )
                .build();
    }

    public static FlightSummary toSummary(Flight f) {
        return new FlightSummary(
                f.getId(),
                f.getAirlineName(),
                f.getLogoUrl(),
                f.getFromCity(),
                f.getToCity(),
                f.getDepartureTime(),
                f.getArrivalTime(),
                f.isRoundTrip(),
                f.getPriceOneWay(),
                f.getPriceRoundTrip(),
                f.getAvailableSeats()
        );
    }
}
