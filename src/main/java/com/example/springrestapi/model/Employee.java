package com.example.springrestapi.model;

import java.sql.Date;
import java.time.LocalDateTime;
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
   
   @ManyToOne
   @JoinColumn(name = "department_id")
   private Department department;
   
//   @OneToMany(mappedBy = "employee")
//   private List<Department> departments;
   
   private Long age = 0L;
   
   private String location;
   
   private String password;
   
   @Email(message= "Please enter the valid email address")
   private String email;
   
   @NotBlank(message= "department should not be null")
   @Column(name = "department")
   private String departmentName;
   
   @CreatationTimestamp
   @Column(name="created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;
   
   @UpdationTimestamp
   @Column(name="updated_at")
   private LocalDateTime updatedAt;
   
public Employee() {
	   
   }
   
   public Employee(EmployeeRequest req) {
	   this.name = req.getName();
   }
   
   

public Employee(String id, @NotBlank(message = "Name should not be null") String name, Department department,
		List<Department> departments, Long age, String location, String password,
		@Email(message = "Please enter the valid email address") String email,
		@NotBlank(message = "department should not be null") String departmentName, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
	super();
	this.id = Long.parseLong(id);
	this.name = name;
	this.department = department;
//	this.departments = departments;
	this.age = age;
	this.location = location;
	this.password = password;
	this.email = email;
	this.departmentName = departmentName;
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

public Department getDepartment() {
	return department;
}

public void setDepartment(Department department) {
	this.department = department;
}

//public List<Department> getDepartments() {
//	return departments;
//}
//
//public void setDepartments(List<Department> departments) {
//	this.departments = departments;
//}

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

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getDepartmentName() {
	return departmentName;
}

public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}

public LocalDateTime getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}

public LocalDateTime getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
}



   
}
