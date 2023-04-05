package com.sanjay.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sanjay.Dto.EmployeeRequest;
import com.sanjay.Dto.EmployeeUpdateRequest;
import com.sanjay.entities.Employee;
import com.sanjay.exceptions.userdefinedexception.EmployeeNotFoundException;

public interface EmployeeService {

	Employee create(EmployeeRequest employeeRequest);
	
	Employee update(EmployeeUpdateRequest employeeUpdateRequest) throws EmployeeNotFoundException;
	
	Employee delete(Long empId) throws EmployeeNotFoundException;
	
	Employee fetchById(Long empId) throws EmployeeNotFoundException;
	
	List<Employee> fetchAll();
	
	List<Employee> fetchAllWithSort(String field);
	
	Page<Employee> fetchAllWithPagination(int offSet, int pageSize);
	
	Page<Employee> fetchAllWithPaginationAndSorting(int offSet, int pageSize, String field);
}
