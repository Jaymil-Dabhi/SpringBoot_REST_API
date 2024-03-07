package com.example.springrestapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springrestapi.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
     List<Employee> findByName(String name);
     
     
//     @Query("SELECT e FROM Employee e WHERE e.department = :department")
     List<Employee> findByDepartmentName(String department);
     
     @Query("FROM Employee WHERE department.name = :name")
     List<Employee> getEmployeesByDeptName(String name);
     
     @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
     List<Employee> getEmployeesByDptId(@Param("departmentId") Long departmentId);
     
     // SELECT * FROM table WHERE name="test" AND location="India"
     List<Employee> findByNameAndLocation(@Param("name") String name, @Param("location") String location);
     
     // SELECT * FROM table WHERE name LIKE "%ram%"
     List<Employee> findByNameContaining(String keyword, Sort sort);
     
     @Query("SELECT e FROM Employee e WHERE e.name = :name OR e.location = :location")
     List<Employee> getEmployeesByNameOrLocation(@Param("name") String name, @Param("location") String location);
     
    // @Query(value = "select * from tbl_employee" nativeQuery = true)
//     List<Employee> getEmployees();
     
     List<Employee> getAllRecords();
     
     @Transactional
     @Modifying
     @Query("DELETE FROM Employee WHERE name = :name")
     Integer deleteEmployeeByName(String name);
     
     @Query("SELECT e.id FROM Employee e WHERE e.id = :id")
     Long findEmployeeById(@Param("id") Long id);
     
     Optional<Employee> findById(Long id);
     
     Employee findByEmail(String email);

	Employee save(Employee employee);

	void deleteById(Long id);


//	List<Employee> findByDepartmentId(Long departmentId);

//	@Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
//	List<Employee> getEmployeesByDeptId(Long departmentId);
     
}
