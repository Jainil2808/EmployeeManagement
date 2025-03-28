package com.employeesystem.emsbackend.repository;


import com.employeesystem.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    Employee findByFirstNameAndEmail(String firstname,String email);
    Employee findByEmail(String email);

    List<Employee> findByDepartmentId(Long departmentId);
}
