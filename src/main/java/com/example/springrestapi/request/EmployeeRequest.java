package com.example.springrestapi.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {

	private String name;
	
	private List<String> department;

	public @NotBlank(message = "Name should not be null") String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDepartments() {
		// TODO Auto-generated method stub
		return null;
	}
}
