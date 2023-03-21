package app.service;

import app.model.*;

import java.util.List;

public interface FlightService {
    Flight save(Flight flight);

    Flight update(Flight flight);

    Flight findById(Integer id);

    List<Flight> findAll();

    boolean delete(Flight flight);

    void addEmployee(Flight flight, Employee employee);

    void addPassenger(Flight flight, Passenger passenger);

    void addBaggage(Flight flight, Baggage baggage);

    void addBooking(Flight flight, Booking booking);
}
