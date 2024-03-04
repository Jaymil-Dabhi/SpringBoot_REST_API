package com.example.springrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springrestapi.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	
     List<Employee> findByName(String name);
     
     
     @Query("SELECT e FROM Employee e WHERE e.department = :department")
     List<Employee> findByDepartmentName(String name);
     
     @Query("FROM Employee WHERE department.name = :name")
     List<Employee> getEmployeesByDeptName(String name);
     
     // SELECT * FROM table WHERE name="test" AND location="india"
     List<Employee> findByNameAndLocation(String name, String location);
     
     // SELECT * FROM table WHERE name LIKE "%ram%"
     List<Employee> findByNameContaining(String keyword, Sort sort);
     
     @Query("FROM Employee WHERE name = :name OR Location = :location")
     List<Employee> getEmployeesByNameOrLocation(String name, String location);
     
//     @Query(value = "select * from tbl_employee" nativeQuery = true)
//     List<Employee> getEmployees();
     
     List<Employee> getAllRecords();
     
     @Transactional
     @Modifying
     @Query("DELETE FROM Employee WHERE name = :name")
     Integer deleteEmployeeByName(String name);
     
     Employee findById(Long id);

	Employee save(Employee employee);

	Object deleteById(Long id);
     
}
