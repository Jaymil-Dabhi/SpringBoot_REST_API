package com.example.springrestapi.dao;


import jakarta.persistence.PersistenceContext;

public class DepartmentDAOImplementation {

	@PersistenceContext
	private DepartmentManager manager;
}
