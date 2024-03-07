package com.example.springrestapi.service;

import java.util.List;

import com.example.springrestapi.model.Employee;

public interface EmployeeService {
	
	Employee createEmployee(Employee employee);
   
	List<Employee> getEmployees(int pageNumber, int pageSize);
	
	Employee saveEmployee (Employee employee);
	
	Employee getSingleEmployee (Long id);
	
	List<Employee> getEmployeesByDptId(Long departmentId);
	
	void deleteEmployee(Long id);
	
//	Employee findByName(String name);
	
	Employee updateEmployee(Employee employee);
	
//	List<Employee> getEmployeesByName(String name);
	
	List<Employee> getEmployeesByNameAndLocation(String name, String location);
	
	List<Employee> getEmployeesByKeyword(String name);
	
	List<Employee> getEmployeesByNameOrLocation(String name, String locaiton);
	
	Integer deleteEmployeeByName(String name);

//	Integer deleteEmployeeName(String name);
	
	
}
