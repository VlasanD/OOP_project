package app.controller.rest;


import app.model.Baggage;
import app.service.BaggageService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/baggage")
public class BaggageController {
    private final BaggageService baggageService=ServiceSinglePointAccess.getBaggageService();

    @GetMapping("/all")
    public ResponseEntity<List<Baggage>> getAllBaggages() {
        return ResponseEntity.status(HttpStatus.OK).body(baggageService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Baggage> getBaggageById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(baggageService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Baggage> createBaggage(@RequestBody Baggage baggage) {
        return ResponseEntity.status(HttpStatus.OK).body(baggageService.save(baggage));
    }

    @PutMapping("/update/{id}/{weight}")
    public ResponseEntity<Baggage> updateBaggageWeight(@PathVariable Integer id, @PathVariable Integer weight) {
        Baggage baggage = baggageService.findById(id);
        baggage.setWeight(weight);
        Baggage baggageUpdated = baggageService.update(baggage);
        return ResponseEntity.status(HttpStatus.OK).body(baggageUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        Baggage baggage = baggageService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(baggageService.delete(baggage));
    }
}
