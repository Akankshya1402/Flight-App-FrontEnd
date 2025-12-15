package com.flightapp.flightservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlineCode;
    private String airlineName;
    private String logoUrl;

    private String fromCity;
    private String toCity;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private boolean roundTrip;

    private Integer availableSeats;

    private BigDecimal priceOneWay;
    private BigDecimal priceRoundTrip;

    public Flight() {}

    /* ===== BUILDER ===== */
    public static class Builder {
        private final Flight flight = new Flight();

        public Builder id(Long id) { flight.id = id; return this; }
        public Builder airlineCode(String v) { flight.airlineCode = v; return this; }
        public Builder airlineName(String v) { flight.airlineName = v; return this; }
        public Builder logoUrl(String v) { flight.logoUrl = v; return this; }
        public Builder fromCity(String v) { flight.fromCity = v; return this; }
        public Builder toCity(String v) { flight.toCity = v; return this; }
        public Builder departureTime(LocalDateTime v) { flight.departureTime = v; return this; }
        public Builder arrivalTime(LocalDateTime v) { flight.arrivalTime = v; return this; }
        public Builder roundTrip(boolean v) { flight.roundTrip = v; return this; }
        public Builder availableSeats(Integer v) { flight.availableSeats = v; return this; }
        public Builder priceOneWay(BigDecimal v) { flight.priceOneWay = v; return this; }
        public Builder priceRoundTrip(BigDecimal v) { flight.priceRoundTrip = v; return this; }

        public Flight build() { return flight; }
    }

    /* ===== GETTERS / SETTERS ===== */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAirlineCode() { return airlineCode; }
    public void setAirlineCode(String airlineCode) { this.airlineCode = airlineCode; }

    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getFromCity() { return fromCity; }
    public void setFromCity(String fromCity) { this.fromCity = fromCity; }

    public String getToCity() { return toCity; }
    public void setToCity(String toCity) { this.toCity = toCity; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public boolean isRoundTrip() { return roundTrip; }
    public void setRoundTrip(boolean roundTrip) { this.roundTrip = roundTrip; }

    public Integer getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }

    public BigDecimal getPriceOneWay() { return priceOneWay; }
    public void setPriceOneWay(BigDecimal priceOneWay) { this.priceOneWay = priceOneWay; }

    public BigDecimal getPriceRoundTrip() { return priceRoundTrip; }
    public void setPriceRoundTrip(BigDecimal priceRoundTrip) { this.priceRoundTrip = priceRoundTrip; }
}
