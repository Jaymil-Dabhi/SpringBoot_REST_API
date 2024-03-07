package com.example.springrestapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
//	@Autowired(required=true)
//	private DepartmentDAOImplementation eDAO;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	@PostMapping("/department/create")
	public ResponseEntity<Department> createDepartment(@RequestBody DepartmentRequest departmentRequest){
		String name = departmentRequest.getName();
		Long employeeId = departmentRequest.getEmployeeId();
		
		Optional<Employee> employeeOptional = eRepo.findById(employeeId);

	    if (employeeOptional.isPresent()) {
	        Employee emp = employeeOptional.get();

	        // Create a new department and assign the employee
	        DepartmentRequest department = new DepartmentRequest();
	        department.setName(name);
	        department.setEmployeeId(employeeId);

	        // Save the department
	        Department savedDepartment = dRepo.save(department);

	        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	    } else {
	        // If the employee with the provided ID is not found
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/departments")
	public List<DepartmentResponse> getDepartments(){
		List<Department> depts = dRepo.findAll();
		List<DepartmentResponse> list = new ArrayList<>();
		depts.forEach(d -> {
		    DepartmentResponse dResponse = new DepartmentResponse();
		    dResponse.setDepartmentName(d.getName());
		    dResponse.setId(d.getId());

		    // Example: Assuming you want to concatenate employee names in a single string
		    String employeeNames = d.getEmployees().stream()
		                                .map(Employee::getName)
		                                .collect(Collectors.joining(", "));

		    dResponse.setEmployeeName(employeeNames);
		    list.add(dResponse);
		});
		return list;
	}
	
	
//	@GetMapping("/departments/all")
//	public ResponseEntity<List<Department>>getDepartments(){
//		return new ResponseEntity<List<Department>>(dService.getDepartments(), HttpStatus.OK);
//	}
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<Department>getDepartmentById(@PathVariable Long id){
		Department department = dRepo.findById(id).orElse(null);
	    if (department != null) {
	        return new ResponseEntity<>(department, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
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
