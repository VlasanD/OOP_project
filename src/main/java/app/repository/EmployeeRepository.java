package app.repository;

import app.model.Employee;

public interface EmployeeRepository extends CRUDRepository<Employee,Integer>{

    Employee findByName(String firstName,String lastName);

    Employee findByUsernameAndPassword(String username, String password);
}
