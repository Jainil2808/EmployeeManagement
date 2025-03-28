package com.employeesystem.emsbackend.controller;

import com.employeesystem.emsbackend.entity.Employee;
import com.employeesystem.emsbackend.repository.EmployeeRepository;
import com.employeesystem.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/department/{departmentId}")
    public ResponseEntity<Employee> createEmployee(@PathVariable("departmentId") Long departmentId,
                                                   @RequestBody Employee employee) {
        Employee emp = employeeService.addEmployee(departmentId, employee);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> findEmployeeByDep(@PathVariable("id") Long id){

        List<Employee>employees=employeeRepository.findByDepartmentId(id);

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) {
        Employee emp = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(emp);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,
                                                   @RequestBody Employee updatedEmployee) {
        Employee emp = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(emp);
    }

    @PutMapping("/{employeeId}/transfer/{departmentId}")
    public ResponseEntity<Employee> transferEmployee(@PathVariable("employeeId") Long employeeId,
                                                     @PathVariable("departmentId") Long departmentId) {
        Employee emp = employeeService.transferEmployeeToDepartment(employeeId, departmentId);
        return ResponseEntity.ok(emp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }

    @GetMapping("/email/{mail}")
    public ResponseEntity<Employee> findByEmployeeEmail(@PathVariable("mail") String email) {
        return ResponseEntity.ok(employeeService.findEmployeeByEmail(email));
    }
}