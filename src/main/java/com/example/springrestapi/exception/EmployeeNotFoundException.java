package com.example.springrestapi.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private Long id;

    public EmployeeNotFoundException(Long id) {
        super("Employee not found for id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
