package com.example.springrestapi.model;

import java.sql.Date;
import java.util.List;

import com.example.springrestapi.request.EmployeeRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_employee")
@NoArgsConstructor
@NamedNativeQuery(name="getAllRecords", query="select * from tbl_employee", resultClass = Employee.class)
@NamedQuery(name="Employee.getAllRecords", query="FROM Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
	
   @NotBlank(message= "Name should not be null")
   private String name;
   
   @OneToOne
   @JoinColumn(name="department_id")
   private @NotBlank(message = "department should not be null") String department;
   
   @OneToMany(mappedBy = "employee")
   private List<Department> departments;
   
   private Long age = 0L;
   
   private String location;
   
   @Email(message= "Please enter the valid email address")
   private String email;
   
//   @NotBlank(message= "department should not be null")
//   @Column(name = "department")
//   private String department;
   
   @CreatationTimestamp
   @Column(name="created_at", nullable = false, updatable = false)
   private Date createdAt;
   
   @UpdationTimestamp
   @Column(name="updated_at")
   private Date updatedAt;
   
   public Employee(EmployeeRequest req) {
	   this.name = req.getName();
   }
   
   public Employee() {
	   
   }

public Employee(Long id, @NotBlank(message = "Name should not be null") String name, List<Department> departments,
		Long age, String location, @Email(message = "Please enter the valid email address") String email,
		@NotBlank(message = "department should not be null") String department, Date createdAt, Date updatedAt) {
	super();
	this.id = id;
	this.name = name;
	this.departments = departments;
	this.age = age;
	this.location = location;
	this.email = email;
	this.department = department;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
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

//public Department getDepartment() {
//	return department;
//}
//
//public void setDepartment(Department department) {
//	this.department = department;
//}

public List<Department> getDepartments() {
	return departments;
}

public void setDepartments(Department dept) {
	this.departments = (List<Department>) dept;
}

public Long getAge() {
	return age;
}

public void setAge(Long age) {
	this.age = age;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}




   
   
}
