package app.controller.rest;


import app.model.*;
import app.service.FlightService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService = ServiceSinglePointAccess.getFlightService();

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.save(flight));
    }

    @PutMapping("/update")
    public ResponseEntity<Flight> update(@RequestBody Flight flight) {
        Flight flightUpdated = flightService.update(flight);
        return ResponseEntity.status(HttpStatus.OK).body(flightUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        Flight flight = flightService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(flightService.delete(flight));
    }

    @PutMapping("/{id}/booking")
    public ResponseEntity<Flight>addBooking(@PathVariable Integer id,@RequestBody Booking booking){
        Flight flight=flightService.findById(id);
        flightService.addBooking(flight,booking);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }

    @PutMapping("/{id}/baggage")
    public ResponseEntity<Flight>addBooking(@PathVariable Integer id,@RequestBody Baggage baggage){
        Flight flight=flightService.findById(id);
        flightService.addBaggage(flight,baggage);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }

    @PutMapping("/{id}/passenger")
    public ResponseEntity<Flight>addBooking(@PathVariable Integer id,@RequestBody Passenger passenger){
        Flight flight=flightService.findById(id);
        flightService.addPassenger(flight,passenger);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }
    @PutMapping("/{id}/employee")
    public ResponseEntity<Flight>addBooking(@PathVariable Integer id,@RequestBody Employee employee){
        Flight flight=flightService.findById(id);
        flightService.addEmployee(flight,employee);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }
}
