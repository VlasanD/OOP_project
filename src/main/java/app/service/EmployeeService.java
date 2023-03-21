package app.service;

import app.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee update(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    boolean delete(Employee employee);

    Employee findByName(String firstName,String lastName);

    Employee login(String username, String password);
}
