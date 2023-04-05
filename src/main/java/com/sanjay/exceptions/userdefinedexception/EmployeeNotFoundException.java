package com.sanjay.exceptions.userdefinedexception;

public class EmployeeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super("Employee Not found!!");
	}
	
	public EmployeeNotFoundException(String message)
	{
		super(message);
	}
}
