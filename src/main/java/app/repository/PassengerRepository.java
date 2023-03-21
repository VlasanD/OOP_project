package app.repository;

import app.model.Employee;
import app.model.Passenger;

public interface PassengerRepository extends CRUDRepository<Passenger,Integer>{
    Passenger findByName(String firstName, String lastName);
}
