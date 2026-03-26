package com.smart.smartemployee.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.smartemployee.entity.ApiResponse;
import com.smart.smartemployee.entity.Employee;
import com.smart.smartemployee.entity.EmployeeDto;
import com.smart.smartemployee.service.EmployeeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	private final EmployeeService service;
	
	@Autowired
	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}
	
//	@PostMapping
//	public ResponseEntity<Employee> save(@Valid @RequestBody EmployeeDto emp){
//		return ResponseEntity.ok(service.save(emp));
//	}
	@PostMapping
	public ApiResponse<Employee> save(@Valid @RequestBody EmployeeDto dto){
		Employee emp=service.save(dto);
		
		return ApiResponse.<Employee>builder()
				.status(200)
				.message("Employee Saved Successfully")
				.data(emp)
				.timestamp(LocalDateTime.now())
				.build();
	}
	
//	@GetMapping
//	public ResponseEntity<List<Employee>> getALl(@RequestParam int page,@RequestParam int size,@RequestParam String sort){
//		return ResponseEntity.ok(service.getAllEmployee(page, size, sort));
//	}
	@GetMapping
	public ApiResponse<List<Employee>> getAll(@RequestParam int page,@RequestParam int size,@RequestParam String sort) {
	 
	    List<Employee> list = service.getAllEmployee(page, size, sort);
	 
	    return ApiResponse.<List<Employee>>builder()
	            .status(200)
	            .message("All Employees")
	            .data(list)
	            .timestamp(LocalDateTime.now())
	            .build();
	}
	 
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Employee> getById(@PathVariable Long id){
//		return ResponseEntity.ok(service.getById(id));
//	}
	@GetMapping("/{id}")
	public ApiResponse<Employee> getById(@PathVariable Long id) {
	 
	    Employee emp = service.getById(id);
	 
	    return ApiResponse.<Employee>builder()
	            .status(200)
	            .message("Employee Found")
	            .data(emp)
	            .timestamp(LocalDateTime.now())
	            .build();
	}
	
	@GetMapping("/latest")
	public ResponseEntity<List<Employee>> getLatestEmployees(){
		return ResponseEntity.ok(service.getFiveLatestEmployees());
	}
	
	@GetMapping("/department/{dept}")
	public ResponseEntity<List<Employee>> getByDept(@PathVariable String dept){
		return ResponseEntity.ok(service.getByDepartment(dept));
	}
	
	@GetMapping("/high-salary")
	public ResponseEntity<List<Employee>> getHighSalary(@RequestParam Double sal){
		return ResponseEntity.ok(service.getHighSalary(sal));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto dto){
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@PutMapping("/{id}/increment")
	public ResponseEntity<Employee> salIncrement(@PathVariable Long id, @RequestParam Double amount){
		return ResponseEntity.ok(service.inctrementSalary(id, amount));
	}
	
//	@DeleteMapping("/{id}")
//	public String delete(@PathVariable Long id){
//		service.delete(id);
//		return "Deleted successfully";
//	}
	@DeleteMapping("/{id}")
	public ApiResponse<String> delete(@PathVariable Long id) {
	 
	    service.delete(id);
	 
	    return ApiResponse.<String>builder()
	            .status(200)
	            .message("Deleted Successfully")
	            .data("Employee Removed")
	            .timestamp(LocalDateTime.now())
	            .build();
	}
	
	@GetMapping("/get_all_employees")
	public ResponseEntity<List<Employee>> getAllListEmployees(){
		
		return ResponseEntity.ok(service.getAllListEmployees());
	}
	 
}

