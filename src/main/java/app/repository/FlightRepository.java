package app.repository;

import app.model.Flight;

public interface FlightRepository extends CRUDRepository<Flight, Integer> {
    Flight findByDepartureAndDestination(String departure, String destination);
}
