package app.controller.rest;


import app.model.Booking;
import app.service.BookingService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

// every request to http://localhost:8080/person will enter on this controller
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService= ServiceSinglePointAccess.getBookingService();

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.save(booking));
    }

    @PutMapping("/update")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking) {
        Booking bookingFromDb = bookingService.findById(booking.getIdBooking());
        bookingFromDb.setDate(booking.getDate());
        bookingFromDb.setPrice(booking.getPrice());
        bookingFromDb.setPassenger(booking.getPassenger());
        Booking updatedBooking=bookingService.update(bookingFromDb);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        Booking booking = bookingService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.delete(booking));
    }
}
