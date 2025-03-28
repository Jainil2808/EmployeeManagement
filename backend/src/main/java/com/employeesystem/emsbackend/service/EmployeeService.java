package com.employeesystem.emsbackend.service;

import com.employeesystem.emsbackend.entity.Department;
import com.employeesystem.emsbackend.entity.Employee;
import com.employeesystem.emsbackend.exception.ResourceNotFoundException;
import com.employeesystem.emsbackend.repository.DepartmentRepository;
import com.employeesystem.emsbackend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public Employee addEmployee(Long departmentId, Employee employee) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Id " + departmentId + " not found"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Id " + employeeId + " not found"));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee emp = findEmployeeById(id);
        emp.setFirstName(updatedEmployee.getFirstName());
        emp.setLastName(updatedEmployee.getLastName());
        emp.setEmail(updatedEmployee.getEmail());
        emp.setRole(updatedEmployee.getRole());
        emp.setSalary(updatedEmployee.getSalary());
        // Department remains the same unless explicitly changed
        if (updatedEmployee.getDepartment() != null) {
            emp.setDepartment(updatedEmployee.getDepartment());
        }
        return employeeRepository.save(emp);
    }

    public Employee transferEmployeeToDepartment(Long employeeId, Long departmentId) {
        Employee employee = findEmployeeById(employeeId);
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Id " + departmentId + " not found"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        boolean exist = employeeRepository.existsById(id);
        if (!exist) {
            throw new ResourceNotFoundException("Employee not found Id " + id);
        }
        employeeRepository.deleteById(id);
    }

    public Employee findEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with email " + email + " not found");
        }
        return employee;
    }
}