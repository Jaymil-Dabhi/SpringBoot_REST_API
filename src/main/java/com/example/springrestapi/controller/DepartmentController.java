package com.example.springrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.dao.DepartmentDAOImplementation;
import com.example.springrestapi.repository.DepartmentRepository;
import com.example.springrestapi.repository.EmployeeRepository;
import com.example.springrestapi.service.DepartmentService;



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
}
