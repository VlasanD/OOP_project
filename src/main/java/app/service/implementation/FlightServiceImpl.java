package app.service.implementation;

import app.model.*;
import app.repository.*;
import app.service.FlightService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository = RepositorySinglePointAccess.getFlightRepository();
    private final EmployeeRepository employeeRepository = RepositorySinglePointAccess.getEmployeeRepository();
    private final PassengerRepository passengerRepository = RepositorySinglePointAccess.getPassengerRepository();
    private final BookingRepository bookingRepository = RepositorySinglePointAccess.getBookingRepository();
    private final BaggageRepository baggageRepository=RepositorySinglePointAccess.getBaggageRepository();

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight update(Flight flight) {
        return flightRepository.update(flight);
    }

    @Override
    public Flight findById(Integer id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public boolean delete(Flight flight) {
        return flightRepository.delete(flight);
    }

    @Override
    public void addEmployee(Flight flight, Employee employee) {
        if (flight.getEmployees() == null)
            flight.setEmployees(new ArrayList<>());

        if (employee.getIdEmployee() == null || employeeRepository.findById(employee.getIdEmployee()) == null)
            employee = employeeRepository.save(employee);

        if (!flight.getEmployees().contains(employee)) {
            flight.getEmployees().add(employee);
            flightRepository.update(flight);
        }
    }

    @Override
    public void addPassenger(Flight flight, Passenger passenger) {
        if (flight.getEmployees() == null)
            flight.setEmployees(new ArrayList<>());

        if (passenger.getIdPassenger() == null || passengerRepository.findById(passenger.getIdPassenger()) == null)
            passenger = passengerRepository.save(passenger);

        Booking booking = bookingRepository.findByPassenger(passenger);

        if (booking != null && flight.getOccupiedSeats() < flight.getAvailableSeats()) {
            flight.setOccupiedSeats(flight.getOccupiedSeats() + 1);
            flight.getPassengers().add(passenger);
            flightRepository.update(flight);
        }
    }

    @Override
    public void addBaggage(Flight flight, Baggage baggage) {
        if (flight.getBaggages() == null)
            flight.setBaggages(new ArrayList<>());

        if (baggage.getIdBaggage() == null || baggageRepository.findById(baggage.getIdBaggage()) == null)
            baggage = baggageRepository.save(baggage);

        if (!flight.getBaggages().contains(baggage)) {
            flight.getBaggages().add(baggage);
            flightRepository.update(flight);
        }
    }

    @Override
    public void addBooking(Flight flight, Booking booking) {
        if (flight.getBookings() == null)
            flight.setBookings(new ArrayList<>());

        if (booking.getIdBooking() == null || bookingRepository.findById(booking.getIdBooking()) == null)
            booking = bookingRepository.save(booking);

        if (!flight.getBookings().contains(booking)) {
            flight.getBookings().add(booking);
            flightRepository.update(flight);
        }
    }
}
