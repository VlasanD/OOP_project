package app.service;

import app.model.Flight;
import app.model.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger save(Passenger passenger);

    Passenger update(Passenger passenger);

    Passenger findById(Integer id);

    List<Passenger> findAll();

    boolean delete(Passenger passenger);
    Passenger findByName(String firstName, String lastName);
}
