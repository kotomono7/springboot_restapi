package com.kotomono.restapi.repository;

import com.kotomono.restapi.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryEmployeeRepository {
    private static final List<Employee> DATABASE = new ArrayList<>();

    static {
        DATABASE.add(new Employee(
                1, "John", "Smith", "john.smith@gmail.com"
        ));
        DATABASE.add(new Employee(
                2, "Alex", "Raymond", "alex.rmd@gmail.com"
        ));
        DATABASE.add(new Employee(
                3, "David", "Blake", "davblake@gmail.com"
        ));
    }

    //add new employee
    public Employee addEmployee(Employee employee) {
        DATABASE.add(employee);
        return employee;
    }

    //get all employees
    public List<Employee> getAllEmployees() {
        return List.copyOf(DATABASE);
    }

    //get employee by id
    public Employee findById(Integer id) {
        return DATABASE
                .stream()
                .filter(emp -> id.equals(emp.getId()))
                .findFirst()
                .orElseThrow();
    }

    //update employee
    public void updateEmployee(Employee employee) {

    }

    //delete employee
    public Boolean deleteById(Integer id) {
        Employee employee = DATABASE
                .stream()
                .filter(emp -> id.equals(emp.getId()))
                .findFirst()
                .orElseThrow();

        DATABASE.remove(employee);

        return Boolean.TRUE;
    }
}
