package com.smart.smartemployee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.smart.smartemployee.entity.Employee;
import com.smart.smartemployee.entity.EmployeeDto;
import com.smart.smartemployee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeEmpl implements EmployeeService{
	@Autowired
	private final EmployeeRepository repo;

	@Override
	public Employee save(EmployeeDto dto) {
		// TODO Auto-generated method stub
		Employee emp=new Employee();
		emp.setName(dto.getName());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());
		emp.setJoiningDate(dto.getJoiningDate());
		return repo.save(emp);
	}

	@Override
	public Employee getById(Long id) {
		// TODO Auto-generated method stub
		Employee emp=repo.findById(id).orElseThrow(()->new RuntimeException("Employee Not Found"));
		return emp;
	}

	@Override
	public List<Employee> getAllEmployee(int page, int size, String sort) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(page, size, Sort.by(sort));
		return repo.findAll(pageable).getContent();
	}

	@Override
	public List<Employee> getFiveLatestEmployees() {
		// TODO Auto-generated method stub
		List<Employee> emplist= repo.findLatestFive();
		return emplist;
	}

	@Override
	public List<Employee> getByDepartment(String dept) {
		// TODO Auto-generated method stub
		return repo.findByDepartment(dept);
	}

	@Override
	public List<Employee> getHighSalary(Double salary) {
		// TODO Auto-generated method stub
		return repo.findBySalaryGreaterThan(salary);
	}

	@Override
	public Employee update(Long id, EmployeeDto dto) {
		// TODO Auto-generated method stub
		Employee emp= repo.findById(id).orElseThrow(()-> new RuntimeException("Employee Not Found"));
		emp.setName(dto.getName());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());
		emp.setJoiningDate(dto.getJoiningDate());
		return repo.save(emp);
	}

	@Override
	public Employee inctrementSalary(Long id, Double amount) {
		// TODO Auto-generated method stub
		Employee employee=repo.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));
		employee.setSalary(employee.getSalary()+amount);
		return employee;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Employee employee=repo.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));
		repo.delete(employee);
	}

	@Override
	public List<Employee> getAllListEmployees() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
}
