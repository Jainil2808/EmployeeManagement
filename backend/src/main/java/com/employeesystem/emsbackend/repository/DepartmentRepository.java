package com.employeesystem.emsbackend.repository;

import com.employeesystem.emsbackend.entity.Department;
import com.employeesystem.emsbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByUser(User user);
    Department findByNameAndUser(String name, User user);
    boolean existsByName(String name);
}