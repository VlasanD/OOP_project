package app.single_point_access;

import app.service.*;
import app.service.implementation.*;

import java.util.List;

public class ServiceSinglePointAccess {
    private static BaggageService baggageService;
    private static BookingService bookingService;
    private static EmployeeService employeeService;
    private static FlightService flightService;
    private static  PassengerService passengerService;

    static {
        baggageService= new BaggageServiceImpl();
        bookingService= new BookingServiceImpl();
        employeeService= new EmployeeServiceImpl();
        flightService= new FlightServiceImpl();
        passengerService= new PassengerServiceImpl();


    }

    public static BaggageService getBaggageService() {
        return baggageService;
    }

    public static BookingService getBookingService() {
        return bookingService;
    }

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    public static FlightService getFlightService() {
        return flightService;
    }

    public static PassengerService getPassengerService() {
        return passengerService;
    }



}
