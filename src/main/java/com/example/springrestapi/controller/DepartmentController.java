package com.example.springrestapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.dao.DepartmentDAOImplementation;
import com.example.springrestapi.model.Department;
import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.DepartmentRepository;
import com.example.springrestapi.repository.EmployeeRepository;
import com.example.springrestapi.request.DepartmentRequest;
import com.example.springrestapi.response.DepartmentResponse;
import com.example.springrestapi.service.DepartmentService;

import jakarta.validation.Valid;



@RestController
public class DepartmentController {
  
	@Autowired
	private DepartmentService dService;
	
	@Autowired
	private DepartmentRepository dRepo;
	
	@Autowired(required=true)
	private DepartmentDAOImplementation eDAO;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	@PostMapping("/department/create")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department){
		String name = department.getName();
		List<Employee> employee = department.getEmployees();
		
		Department saveDepartment = dRepo.save(department);
		
		return new ResponseEntity<Department>(dService.createDepartment(department),HttpStatus.CREATED);
	}
	
	@GetMapping("/departments")
	public List<DepartmentResponse> getDepartments(){
		List<Department> depts = dRepo.findAll();
		List<DepartmentResponse> list = new ArrayList<>();
		depts.forEach(d -> {
			DepartmentResponse dResponse = new DepartmentResponse();
			dResponse.setDepartmentName(d.getName());
			dResponse.setId(d.getId());
			dResponse.setEmployeeName(((Department) d.getEmployees()).getName());
			list.add(dResponse);
		});
		return list;
	}
	
	
//	@GetMapping("/departments/all")
//	public ResponseEntity<List<Department>>getDepartments(){
//		return new ResponseEntity<List<Department>>(dService.getDepartments(), HttpStatus.OK);
//	}
	
	public ResponseEntity<Department>getDepartmentById(@PathVariable Long id){
		return new ResponseEntity<Department>(HttpStatus.OK);
	}
	
	
//	@PostMapping()
//	public ResponseEntity<Department>saveDepartmentWithRequest(@Valid @RequestBody DepartmentRequest dRequest){
//		Employee emp = new Employee();
//		emp.setName(dRequest.getEmployees);
//		
//		emp = eRepo.save(emp);
//		
//		Department department = new Department(dRequest);
//		department.setEmployees(emp);
//		
//		department = dRepo.save(department);
//		return new ResponseEntity<Department>(department, HttpStatus.CREATED);
//	}
}
