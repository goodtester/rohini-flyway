DROP DATABASE IF EXISTS flyway;
CREATE DATABASE IF NOT EXISTS flyway;
USE flyway;

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    userId INTEGER NOT NULL AUTO_INCREMENT,
    email VARCHAR(128) NOT NULL,
    password VARCHAR(64) NOT NULL,
    firstname VARCHAR(128) NOT NULL,
    lastname VARCHAR(128) NOT NULL,
    address VARCHAR(256) NOT NULL,
    phone VARCHAR(32) NOT NULL,
    active BOOLEAN NOT NULL,
    userType INT NOT NULL, -- 1: Admin, 0: End-user
    --
    PRIMARY KEY (userId)
);

DROP TABLE IF EXISTS airlines;
CREATE TABLE airlines
(
    airlineId INTEGER NOT NULL AUTO_INCREMENT,
    airlineCode VARCHAR(10) NOT NULL,
    airlineName VARCHAR(128) NOT NULL,
    --
    PRIMARY KEY (airlineId)
);

DROP TABLE IF EXISTS airports;
CREATE TABLE airports
(
    airportId INTEGER NOT NULL AUTO_INCREMENT,
    airportCode VARCHAR(10) NOT NULL,
    airportName VARCHAR(128) NOT NULL,
    airportCity VARCHAR(128),
    airportCountry VARCHAR(128),
    --
    PRIMARY KEY (airportId)
);

DROP TABLE IF EXISTS flights;
CREATE TABLE flights
(
    flightId INTEGER NOT NULL AUTO_INCREMENT,
    airlineCode VARCHAR(10) NOT NULL,
    flightNumber VARCHAR(10) NOT NULL,
    sourceAirport VARCHAR(10) NOT NULL,
    destinationAirport VARCHAR(10) NOT NULL,
    startTime VARCHAR(20) NOT NULL,
    endTime VARCHAR(20) NOT NULL,
    price VARCHAR(20) NOT NULL,
    totalSeats SMALLINT NOT NULL,
    availableSeats SMALLINT,
    stops SMALLINT,
    duration VARCHAR(32),
    --
    PRIMARY KEY (flightId)
);


INSERT users (email, password, firstname, lastname, address, phone, active, userType)
VALUES ('admin@flyway.com', 'admin1', 'Fnadmin', 'Lnadmin', '123 Main St., Nowhere Town, NJ 12345', '2345678909', TRUE, 1);

INSERT airlines (airlineCode, airlineName)
VALUES ('DL', 'Delta Airlines');

INSERT airports (airportCode, airportName)
VALUES ('ATL', 'Atlanta');
INSERT airports (airportCode, airportName)
VALUES ('SFO', 'San Francisco');

INSERT flights (flightNumber, airlineCode, sourceAirport, destinationAirport, startTime, endTime, price, totalSeats, availableSeats, stops, duration)
VALUES ('123', 'DL', 'ATL', 'SFO', '2022-06-01 12:00 PM', '2022-06-01 20:00 PM', 100.00, 100, 95, 0, '10h 0m');
