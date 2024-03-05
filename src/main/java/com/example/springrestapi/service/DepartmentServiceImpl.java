package com.example.springrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springrestapi.model.Department;
import com.example.springrestapi.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository dRepository;

	@Override
	public Department getDepartmentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return dRepository.findAll();
	}

	@Override
	public Department createDepartment(Department department) {
		
		return dRepository.save(department);
	}

	@Override
	public Optional<Department> getSingleDepartment(Long id) {
		Department department = new Department();
		return dRepository.findById(id);
	}

}
