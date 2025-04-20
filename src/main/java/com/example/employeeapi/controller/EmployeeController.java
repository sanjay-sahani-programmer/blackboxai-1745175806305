package com.example.employeeapi.controller;

import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.findById(id)
                .map(existingEmployee -> {
                    employee.setId(existingEmployee.getId());
                    Employee updatedEmployee = employeeService.save(employee);
                    return ResponseEntity.ok(updatedEmployee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(existingEmployee -> {
                    employeeService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
