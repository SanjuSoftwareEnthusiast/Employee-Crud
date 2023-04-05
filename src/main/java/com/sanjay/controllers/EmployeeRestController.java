package com.sanjay.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjay.Dto.EmployeeRequest;
import com.sanjay.Dto.EmployeeUpdateRequest;
import com.sanjay.entities.Employee;
import com.sanjay.exceptions.userdefinedexception.EmployeeNotFoundException;
import com.sanjay.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add-employee")
	public ResponseEntity<?> create(@Valid@RequestBody EmployeeRequest employeeRequest)
	{
		Employee employee = employeeService.create(employeeRequest);
		
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-employee")
	public ResponseEntity<?> update(@Valid@RequestBody EmployeeUpdateRequest employeeUpdateRequest) throws EmployeeNotFoundException
	{
		Employee employee = employeeService.update(employeeUpdateRequest);
		
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-employee/{empId}")
	public ResponseEntity<?> delete(@PathVariable Long empId) throws EmployeeNotFoundException
	{
		Employee employee = employeeService.delete(empId);
		
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> fetchAll()
	{
		List<Employee> employeeList = employeeService.fetchAll();
		
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}
	
	@GetMapping("/sort/{field}")
	public ResponseEntity<?> fetchAllWithSort(@PathVariable String field)
	{
		List<Employee> employeeList = employeeService.fetchAllWithSort(field);
		
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}
	
	@GetMapping("/pagination/{offSet}/{pageSize}")
	public ResponseEntity<?> fetchAllWithPagination(@PathVariable int offSet, @PathVariable int pageSize)
	{
		Page<Employee> employeeList = employeeService.fetchAllWithPagination(offSet, pageSize);
		
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}
	
	@GetMapping("/sort-pagination/{offSet}/{pageSize}/{field}")
	public ResponseEntity<?> fetchAllWithPagination(@PathVariable int offSet, @PathVariable int pageSize, @PathVariable String field)
	{
		Page<Employee> employeeList = employeeService.fetchAllWithPaginationAndSorting(offSet, pageSize, field);
		
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}
	
	@GetMapping("fetch-by-id/{empId}")
	public ResponseEntity<?> fetchById(@PathVariable Long empId) throws EmployeeNotFoundException
	{
		Employee employee = employeeService.fetchById(empId);
		
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
