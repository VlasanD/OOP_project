package app.controller.rest;


import app.model.Passenger;
import app.service.PassengerService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

// every request to http://localhost:8080/person will enter on this controller
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService = ServiceSinglePointAccess.getPassengerService();

    // Map http://localhost:8080/person/all
    // Get - to take data
    @GetMapping("/all")
    public ResponseEntity<List<Passenger>> getAllEmployees() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(passengerService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    @GetMapping("/id/{id}")
    public ResponseEntity<Passenger> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(passengerService.findById(id));
    }

    @GetMapping("{firstName}/{lastName}")
    public ResponseEntity<Passenger> getEmployeeByName(@PathVariable String firstName,@PathVariable String lastName){
        return ResponseEntity.status(HttpStatus.OK).body(passengerService.findByName(firstName, lastName));
    }

    // RequestBody - is the data sent to server through request
    // Post - create data
    @PostMapping("/create")
    public ResponseEntity<Passenger> createEmployee(@RequestBody Passenger passenger) {
        return ResponseEntity.status(HttpStatus.OK).body(passengerService.save(passenger));
    }

    @PutMapping("/update")
    public ResponseEntity<Passenger> update(@RequestBody Passenger passenger) {
        Passenger passengerUpdated = passengerService.update(passenger);
        return ResponseEntity.status(HttpStatus.OK).body(passengerUpdated);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        Passenger passenger = passengerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(passengerService.delete(passenger));
    }
}
