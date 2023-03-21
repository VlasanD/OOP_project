package app.repository;

import app.model.Booking;
import app.model.Flight;
import app.model.Passenger;

import java.util.Date;

public interface BookingRepository extends CRUDRepository<Booking,Integer>{
    Booking findByPassenger(Passenger passenger);
}
