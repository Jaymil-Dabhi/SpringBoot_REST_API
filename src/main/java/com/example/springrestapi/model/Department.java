package com.example.springrestapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
	
   @NotBlank(message= "Name should not be null")
   private String name;
   
   @ManyToOne
   @JoinColumn(name = "employee_id")
   private Employee employee;
   
  public Department() {
	  
  }
   

public Department(Long id, @NotBlank(message = "Name should not be null") String name, Employee employee) {
	super();
	this.id = id;
	this.name = name;
	this.employee = employee;
}


public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Employee getEmployee() {
	return employee;
}

public void setEmployee(Employee employee) {
	this.employee = employee;
}
   
   
   
}
