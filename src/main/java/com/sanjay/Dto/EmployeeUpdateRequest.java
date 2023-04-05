package com.sanjay.Dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequest {

	@NotNull
	private Long employeeId;
	
	private String employeeName;
	
	private String officeEmail;
	
	private Double salary;
	
	private Integer deptNo;
	
	private String designation;
}
