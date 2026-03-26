package com.smart.smartemployee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smart.smartemployee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByDepartment(String dept);
	
	List<Employee> findBySalaryGreaterThan(Double salary);
	
	@Query(value = "SELECT * FROM employees ORDER BY id DESC LIMIT 5", nativeQuery = true)
	List<Employee> findLatestFive();
}
