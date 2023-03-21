package app.service.implementation;

import app.model.Employee;
import app.repository.EmployeeRepository;
import app.service.EmployeeService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository = RepositorySinglePointAccess.getEmployeeRepository();

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean delete(Employee employee) {
        return employeeRepository.delete(employee);
    }

    @Override
    public Employee findByName(String firstName, String lastName) {
        return employeeRepository.findByName(firstName, lastName);
    }

    @Override
    public Employee login(String username, String password) {
        return employeeRepository.findByUsernameAndPassword(username, password);
    }
}
