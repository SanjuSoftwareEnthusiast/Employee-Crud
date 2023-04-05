package com.sanjay.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sanjay.Dto.EmployeeRequest;
import com.sanjay.Dto.EmployeeUpdateRequest;
import com.sanjay.entities.Employee;
import com.sanjay.exceptions.userdefinedexception.EmployeeNotFoundException;
import com.sanjay.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee create(EmployeeRequest employeeRequest) {
		
		Employee employee = Employee.builder()
							.deptNo(employeeRequest.getDeptNo())
							.designation(employeeRequest.getDesignation())
							.employeeName(employeeRequest.getEmployeeName())
							.isDeleted(false)
							.officeEmail(employeeRequest.getOfficeEmail())
							.salary(employeeRequest.getSalary())
							.build();
		employee = employeeRepository.save(employee);
		return employee;
	}

	@Override
	public Employee update(EmployeeUpdateRequest employeeUpdateRequest) throws EmployeeNotFoundException {
		Employee employee = fetchById(employeeUpdateRequest.getEmployeeId());
		
		if(employeeUpdateRequest.getDeptNo()!=null)
		{
			employee.setDeptNo(employeeUpdateRequest.getDeptNo());
		}
		
		if(employeeUpdateRequest.getDesignation()!=null && employeeUpdateRequest.getDesignation().length()!=0)
		{
			employee.setDesignation(employeeUpdateRequest.getDesignation());
		}
		
		if(employeeUpdateRequest.getEmployeeName()!=null && employeeUpdateRequest.getEmployeeName().length()!=0)
		{
			employee.setEmployeeName(employeeUpdateRequest.getEmployeeName());
		}
		
		if(employeeUpdateRequest.getOfficeEmail()!=null && !employeeUpdateRequest.getOfficeEmail().isEmpty())
		{
			employee.setOfficeEmail(employeeUpdateRequest.getOfficeEmail());
		}
		
		if(employeeUpdateRequest.getSalary()!=null)
		{
			employee.setSalary(employeeUpdateRequest.getSalary());
		}
		
		employee = employeeRepository.save(employee);
		
		return employee;
	}

	@Override
	public Employee delete(Long empId) throws EmployeeNotFoundException {
		//Basically deleting an employee from a database is not a good practice so we will mark isDelete as true
		Employee employee = fetchById(empId);
		
		employee.setDeleted(true);
		
		employee = employeeRepository.save(employee);
		
		return employee;
	}

	@Override
	public Employee fetchById(Long empId) throws EmployeeNotFoundException {
		Optional<Employee> employeeOptional = employeeRepository.findById(empId);
		Employee employee = null;
		
		try {
			employee = employeeOptional.get();
		}
		catch (Exception e) {
			throw new EmployeeNotFoundException("Employee with the Id not found In our records");
		}
		return employee;
	}

	@Override
	public List<Employee> fetchAll() {
		
		List<Employee> empLists = employeeRepository.findAll().stream()
                .filter(emp -> !emp.isDeleted())
                .collect(Collectors.toList());
		return empLists;
	}

	@Override
	public List<Employee> fetchAllWithSort(String field) {
		
		List<Employee> employees = employeeRepository.findAll(Sort.by(Direction.ASC, field));
		
		return employees;
	}

	@Override
	public Page<Employee> fetchAllWithPagination(int offSet, int pageSize) {
		
		Page<Employee> employees = employeeRepository.findAll(PageRequest.of(offSet, pageSize));
		return employees;
	}

	@Override
	public Page<Employee> fetchAllWithPaginationAndSorting(int offSet, int pageSize, String field) {
		
		//Page<Employee> employees = employeeRepository.findAll(PageRequest.of(offSet, pageSize, Sort.by(field)));
		
		Page<Employee> employees = employeeRepository.findAll(PageRequest.of(offSet, pageSize).withSort(Sort.by(field)));
		
		return employees;
	}

}
