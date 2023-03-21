package app.controller.rest;


import app.model.Employee;
import app.service.EmployeeService;
import app.single_point_access.ServiceSinglePointAccess;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService = ServiceSinglePointAccess.getEmployeeService();

    // Map http://localhost:8080/person/all
    // Get - to take data
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findById(id));
    }

    @GetMapping("{firstName}/{lastName}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String firstName,@PathVariable String lastName){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByName(firstName, lastName));
    }

    // RequestBody - is the data sent to server through request
    // Post - create data
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employee));
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        Employee employeeFromDb = employeeService.findById(employee.getIdEmployee());
        employeeFromDb.setFirstName(employee.getFirstName());
        employeeFromDb.setLastName(employee.getLastName());
        employeeFromDb.setUsername(employee.getUsername());
        employeeFromDb.setPassword(employee.getPassword());
        employeeFromDb.setRole(employee.getRole());
        Employee employeeUpdated = employeeService.update(employeeFromDb);
        return ResponseEntity.status(HttpStatus.OK).body(employeeUpdated);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.delete(employee));
    }
}
