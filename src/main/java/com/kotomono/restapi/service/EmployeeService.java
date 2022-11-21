package com.kotomono.restapi.service;

import com.kotomono.restapi.model.Employee;

import java.util.List;

public interface EmployeeService {
    //add new employee
    Employee addEmployee(Employee employee);

    //get all employees
    List<Employee> getAllEmployees();

    //get employee by id
    Employee findById(Integer id);

    //update employee
    void updateEmployee(Employee employee);

    //delete employee
    Boolean deleteById(Integer id);
}
