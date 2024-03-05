
package com.example.springrestapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository eRepository;

	
	@Override
	public Employee createEmployee(Employee employee) {
		
		return eRepository.save(employee);
	}
	
//	public Employee findByName(String name) {
//       
//		for (Employee employee : employeeList) {
//            if (employee.getName().equals(name)) {
//                return employee;
//            }
//        }
//        // If not found, return null or throw an exception based on your design
//        return null;
//    }

	@Override
	public List<Employee> getEmployees(int pageNumber, int pageSize) {
		PageRequest pages = PageRequest.of(pageNumber, pageSize, Direction.DESC, "id");
		return eRepository.findAll(pages).getContent();
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return eRepository.save(employee);
	}
	
	@Override
	public Employee getSingleEmployee(Long id) {  
		Employee employee = new Employee();
		if(employee.isPresent()) {
			return employee.getId();
		}
		throw new RuntimeException("Employee is not found for the id "+id);
	}
	
	@Override
	public void deleteEmployee(Long id) {
		return eRepository.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return eRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		return eRepository.findByName(name);
	}
	
	@Override
	public List<Employee> getEmployeesByNameAndLocation(String name, String location){
		return eRepository.findByNameAndLocation(name, location);
	}
	
	@Override
	public List<Employee> getEmployeesByKeyword(String name){
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return eRepository.findByNameContaining(name, sort);
	}

    @Override
    public List<Employee> getEmployeesByNameOrLocation(String name, String location){
    	return eRepository.getEmployeesByNameOrLocation(name, location);
    }
    
    @Override
    public Integer deleteEmployeeName(String name) {
    	return eRepository.deleteEmployeeByName(name);
    }

	@Override
	public Integer deleteEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByDptId(Long id) {
		// TODO Auto-generated method stub
		return (Employee) eRepository.getEmployeesByDeptId(id);
	}

	
	
}
