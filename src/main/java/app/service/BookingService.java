package app.service;

import app.model.Booking;
import app.model.Employee;
import app.model.Flight;
import app.model.Passenger;

import java.util.Date;
import java.util.List;

public interface BookingService {
    Booking save(Booking booking);

    Booking update(Booking booking);

    Booking findById(Integer id);

    List<Booking> findAll();

    boolean delete(Booking booking);

    Booking findByPassenger(Passenger passenger);
}
