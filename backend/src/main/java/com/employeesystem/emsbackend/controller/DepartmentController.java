package com.employeesystem.emsbackend.controller;

import com.employeesystem.emsbackend.dto.DepartmentDto;
import com.employeesystem.emsbackend.entity.Department;
import com.employeesystem.emsbackend.repository.DepartmentRepository;
import com.employeesystem.emsbackend.service.DepartmentService;
import com.employeesystem.emsbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    private final DepartmentService departmentService;
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<Department>> getDepartment(){

        List<Department>departments=departmentRepository.findAll();

        return ResponseEntity.ok(departments);

    }

    @PostMapping("/")
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDto departmentDto) {
        Department dept = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(dept, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long id) {
        Department dept = departmentService.findDepartmentById(id);
        return ResponseEntity.ok(dept);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Department>> getAllDepartments(@PathVariable("userId") Long userId) {
        List<Department> departments = departmentService.getAllDepartments(userId);
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable("id") Long id,
            @RequestBody DepartmentDto updatedDepartmentDto
    ) {
        Department dept = departmentService.updateDepartment(id, updatedDepartmentDto);
        return ResponseEntity.ok(dept);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") Long id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok("Department Deleted Successfully");
    }

    @GetMapping("/name/{name}/user/{userId}")
    public ResponseEntity<Department> findDepartmentByName(
            @PathVariable("name") String name,
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(departmentService.findDepartmentByName(name, userId));
    }
}