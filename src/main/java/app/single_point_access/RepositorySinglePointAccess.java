package app.single_point_access;

import app.model.Employee;
import app.model.Flight;
import app.model.Passenger;
import app.repository.*;
import app.repository.implemetation.*;

import java.util.List;

public class RepositorySinglePointAccess {

    private static BaggageRepository baggageRepository;
    private static BookingRepository bookingRepository;
    private static EmployeeRepository employeeRepository;
    private static PassengerRepository passengerRepository;
    private static FlightRepository flightRepository;

    static {


        baggageRepository = new BaggageRepositoryImpl();
        bookingRepository = new BookingRepositoryImpl();
        employeeRepository = new EmployeeRepositoryImpl();
        passengerRepository = new PassengerRepositoryImpl();
        flightRepository = new FlightRepositoryImpl();
    }

    public static BaggageRepository getBaggageRepository() {
        return baggageRepository;
    }

    public static BookingRepository getBookingRepository() {
        return bookingRepository;
    }

    public static EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public static PassengerRepository getPassengerRepository() {
        return passengerRepository;
    }

    public static FlightRepository getFlightRepository() {
        return flightRepository;
    }

}
