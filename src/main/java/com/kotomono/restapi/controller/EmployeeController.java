package com.kotomono.restapi.controller;

import com.kotomono.restapi.model.Employee;
import com.kotomono.restapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping(path = "/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(@Qualifier(value = "mySqlEmployeeService") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')") // ROLE_ADMIN
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
//        employee.setId(employeeService.getAllEmployees().size() + 1);
        employeeService.addEmployee(employee);
        return ResponseEntity.created(getLocation(employee.getId())).body(employee);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateEmployee() {
        return null;
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }

    protected static URI getLocation(Integer id) {
        return fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}
