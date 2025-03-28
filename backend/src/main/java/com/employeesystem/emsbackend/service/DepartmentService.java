package com.employeesystem.emsbackend.service;

import com.employeesystem.emsbackend.dto.DepartmentDto;
import com.employeesystem.emsbackend.entity.Department;
import com.employeesystem.emsbackend.entity.User;
import com.employeesystem.emsbackend.exception.ResourceNotFoundException;
import com.employeesystem.emsbackend.repository.DepartmentRepository;
import com.employeesystem.emsbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public Department addDepartment(DepartmentDto departmentDto) {
        // Find the user
   User user = userRepository.findById(departmentDto.getUserId())
           .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + departmentDto.getUserId()));

        // Create new department
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
       department.setUser(user);

        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Id " + departmentId + " not found"));
    }

    public List<Department> getAllDepartments(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        System.out.println(departmentRepository.findByUser(user));
        return departmentRepository.findByUser(user);
    }

    public Department updateDepartment(Long id, DepartmentDto updatedDepartmentDto) {
        Department dept = findDepartmentById(id);
        dept.setName(updatedDepartmentDto.getName());
        dept.setDescription(updatedDepartmentDto.getDescription());
        return departmentRepository.save(dept);
    }

    public void deleteDepartmentById(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Department not found Id " + id);
        }
        departmentRepository.deleteById(id);
    }

    public Department findDepartmentByName(String name, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Department department = departmentRepository.findByNameAndUser(name, user);
        if (department == null) {
            throw new ResourceNotFoundException("Department with name " + name + " not found");
        }
        return department;
    }
}