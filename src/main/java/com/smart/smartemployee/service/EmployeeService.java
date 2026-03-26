package com.smart.smartemployee.service;

import java.util.List;

import com.smart.smartemployee.entity.Employee;
import com.smart.smartemployee.entity.EmployeeDto;

public interface EmployeeService {
	//post data
	Employee save(EmployeeDto dto);
	//get by id
	Employee getById(Long id);
	//get all employees paging and sorting
	List<Employee> getAllEmployee(int page, int size, String sort);
	//get latest first five employee
	List<Employee> getFiveLatestEmployees();
	//get By department
	List<Employee> getByDepartment(String dept);
	//get high salary
	List<Employee> getHighSalary(Double salary);
	//put update employee
	Employee update(Long id, EmployeeDto dto);
	//put increment salary
	Employee inctrementSalary(Long id, Double amount);
	//delete
	void delete(Long id);
	
	List<Employee> getAllListEmployees();
}
