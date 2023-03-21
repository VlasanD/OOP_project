package app.service.implementation;

import app.model.Booking;
import app.model.Flight;
import app.model.Passenger;
import app.repository.BookingRepository;
import app.service.BookingService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository= RepositorySinglePointAccess.getBookingRepository();
    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.update(booking);
    }

    @Override
    public Booking findById(Integer id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean delete(Booking booking) {
        return bookingRepository.delete(booking);
    }

    @Override
    public Booking findByPassenger(Passenger passenger) {
        return bookingRepository.findByPassenger(passenger);
    }
}
