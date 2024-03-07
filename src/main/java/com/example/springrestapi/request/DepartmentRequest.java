package com.example.springrestapi.request;

import com.example.springrestapi.model.Employee;

public class DepartmentRequest {

	private String name;
	private Long employeeId;
	public String Employees;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployees() {
		return Employees;
	}

	public void setEmployees(String employees) {
		Employees = employees;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	
	
	
	
}
