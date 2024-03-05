package com.example.springrestapi.service;

import java.util.List;
import java.util.Optional;

import com.example.springrestapi.model.Department;

public interface DepartmentService {

	Department getDepartmentByName(String name);

	List<Department> getDepartments();

	Department createDepartment(Department department);

	Optional<Department> getSingleDepartment(Long id);
}
