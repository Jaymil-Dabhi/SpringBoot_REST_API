package com.example.springrestapi.repository;

import com.example.springrestapi.model.Department;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	public Department save(Department d);
	
    Optional<Department> findById(Long id);

}
