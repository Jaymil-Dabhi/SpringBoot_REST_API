package com.example.springrestapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.springrestapi.config.JwtProvider;
import com.example.springrestapi.dao.EmployeeDAOImplementation;
import com.example.springrestapi.exception.EmployeeException;
import com.example.springrestapi.model.Department;
import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.DepartmentRepository;
import com.example.springrestapi.repository.EmployeeRepository;
import com.example.springrestapi.request.EmployeeRequest;
import com.example.springrestapi.response.AuthResponse;
import com.example.springrestapi.response.EmployeeResponse;
import com.example.springrestapi.service.EmployeeService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	// localhost:8080/api/v1/employees

	@Autowired
	private EmployeeService eService;

	@Autowired
	private EmployeeRepository eRepo;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private EmployeeDAOImplementation eDAO;

	@Autowired
	private DepartmentRepository dRepo;

	@PostMapping("/employees/create")
	public ResponseEntity<AuthResponse> createEmployee(@RequestBody Employee employee) throws EmployeeException {

		String name = employee.getName();
		Long age = employee.getAge();
		String location = employee.getLocation();
		String email = employee.getEmail();
		String departmentName = employee.getDepartmentName();
		String password = employee.getPassword();
		
		
		Employee isEmailExist = eRepo.findByEmail(email);

		if (isEmailExist != null) {
			throw new EmployeeException("Email is Already Used");
		}

		Employee createdEmployee = new Employee();
		createdEmployee.setName(name);
		createdEmployee.setAge(age);
		createdEmployee.setLocation(location);
		createdEmployee.setEmail(email);
		createdEmployee.setDepartmentName(departmentName);
		createdEmployee.setPassword(password);
		

		Employee savedEmployee = eRepo.save(createdEmployee);
		System.out.print(savedEmployee);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedEmployee.getEmail(),
				savedEmployee.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse(token, "Bearer " + token);
//		authResponse.setJwt(token);
		authResponse.setMessage("Employee Created Successfully");

		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}

	@GetMapping("/employees/paged")
	public ResponseEntity<List<Employee>> getEmployees(
			@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize){
		return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
	}
	
//	@GetMapping("/employees")
//	public ResponseEntity<List<EmployeeResponse>> getEmployees(){
//		List<Employee> list = eRepo.findAll();
//		List<EmployeeResponse> responseList = new ArrayList<>();
//		list.forEach(e -> {
//			EmployeeResponse eResponse = new EmployeeResponse();
//			eResponse.setId(e.getId());
//			eResponse.setEmployeeName(e.getName());
//			List<String> depts = new ArrayList<>();
//			for(Department d : e.getDepartments()) {
//				depts.add(d.getName());
//			}
//			eResponse.setDepartment(depts);
//			responseList.add(eResponse);
//		});
//		return new ResponseEntity<List<EmployeeResponse>>(responseList, HttpStatus.OK);
//	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
	}

	@PostMapping("/employees/save")
	public Employee saveEmployee(@Valid @RequestBody Employee employee) {
		return eRepo.save(employee);
	}

	// One to One Relationship
//	@PostMapping("/employees")
//	public ResponseEntity<Employee> saveEmployeeWithRequest(@Valid @RequestBody EmployeeRequest eRequest) {
//		Department dept = new Department();
//		dept.setName(eRequest.getDepartment());
//
//		dept = dRepo.save(dept);
//
//		Employee employee = new Employee(eRequest);
//		employee.setDepartment(dept);
//
//		employee = eRepo.save(employee);
//		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
//	}

	// One to Many Relationship
//   @PostMapping("/employees")
//   public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) {
//	   
//	   Employee employee = new Employee(eRequest);
//	   employee = eRepo.save(employee);
//	   
//	   for(String s : eRequest.getDepartment()) {
//		   Department d = new Department();
//		   d.setName(s);
//		   d.setEmployee(employee);
//		   
//		   dRepo.save(d);
//	   }
//	   return new ResponseEntity<String>("Record saved successfully", HttpStatus.CREATED);
//   }

//   public ResponseEntity<Employee> findByName(@RequestParam String name){
//	   return new ResponseEntity<Employee>(eService.findByName(name), HttpStatus.OK);
//   }

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {	
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/employees/filterByName/{name}")
	public ResponseEntity<List<Employee>> getEmployeesByDepartment(@RequestParam String name) {
//		return new ResponseEntity<List<Employee>>(eRepo.findByDepartmentName(name), HttpStatus.OK);
		return new ResponseEntity<List<Employee>>(eRepo.getEmployeesByDeptName(name), HttpStatus.OK);
	}

	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name,
			@RequestParam String location) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location),
				HttpStatus.OK);
	}

	@GetMapping("/employees/filterByKeyword")
	public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByKeyword(name), HttpStatus.OK);
	}

	@GetMapping("/employees/{name}/{location}")
	public ResponseEntity<List<Employee>> getEmployeesByNameOrLocation(@PathVariable String name,
			@PathVariable String location) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameOrLocation(name, location), HttpStatus.OK);
	}

	@DeleteMapping("/employees/delete/{name}")
	public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
		return new ResponseEntity<String>(eService.deleteEmployeeByName(name) + " No. of records deleted",
				HttpStatus.OK);
	}

	@GetMapping("/employees/filter/{departmentId}")
	public ResponseEntity<List<Employee>> getEmployeesByDeptId(@PathVariable Long departmentId) throws MethodArgumentTypeMismatchException {
//	   return new ResponseEntity<List<Employee>>(eRepo.findByDepartmentName(name), HttpStatus.OK);	
		return new ResponseEntity<List<Employee>>(eRepo.getEmployeesByDptId(departmentId), HttpStatus.OK);
	}

	@GetMapping("/employees/all")
	public List<Employee> getEmployees() {
	   return eRepo.getEmployees();	
//	   return eDAO.getAll();
//		return eRepo.getAllRecords();
	}
}
