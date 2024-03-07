package com.example.springrestapi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_department")
@NoArgsConstructor
@NamedNativeQuery(name="getAllDepartments", query="select * from tbl_department", resultClass = Department.class)
@NamedQuery(name="Department.getAllDepartments", query="FROM Department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
	
   @NotBlank(message= "Name should not be null")
   private String name;
   
   @ManyToOne
   @JoinColumn(name = "employee_id")
   private Employee employee;
   
   @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
   private List<Employee> employees;
   
  public Department() {
	  
  }

public Department(Long id, @NotBlank(message = "Name should not be null") String name, Employee employee,
		List<Employee> employees) {
	super();
	this.id = id;
	this.name = name;
	this.employee = employee;
	this.employees = employees;
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

public List<Employee> getEmployees() {
	return employees;
}

public void setEmployees(List<Employee> employees) {
	this.employees = employees;
}



}
