package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Employee;


import javax.persistence.Id;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    public List<Employee> findByName(String name);
    Employee findFirstByName(String name);
    public List<Employee> findByNameContaining(String name);
    public Employee findById(Id Id);
}
