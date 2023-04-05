package com.sanjay.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

	@NotEmpty
	private String employeeName;
	
	@NotEmpty
	private String officeEmail;
	
	@NotNull
	private Double salary;
	
	@NotNull
	private Integer deptNo;
	
	@NotEmpty
	private String designation;
}
