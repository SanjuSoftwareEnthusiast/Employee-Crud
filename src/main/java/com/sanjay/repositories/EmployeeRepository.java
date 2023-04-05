package com.sanjay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
