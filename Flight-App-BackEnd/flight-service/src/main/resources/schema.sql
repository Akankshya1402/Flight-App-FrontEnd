CREATE TABLE flights (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    airlineCode VARCHAR(20) NOT NULL,
    airlineName VARCHAR(100) NOT NULL,
    logoUrl VARCHAR(255),
    fromCity VARCHAR(100) NOT NULL,
    toCity VARCHAR(100) NOT NULL,
    departureTime DATETIME NOT NULL,
    arrivalTime DATETIME NOT NULL,
    roundTrip BOOLEAN NOT NULL,
    availableSeats INT NOT NULL,
    priceOneWay DECIMAL(10,2) NOT NULL,
    priceRoundTrip DECIMAL(10,2)
);