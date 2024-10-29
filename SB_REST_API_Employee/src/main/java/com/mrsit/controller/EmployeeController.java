package com.mrsit.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrsit.entity.Employee;

@RestController
public class EmployeeController {
	
	@DeleteMapping(value="/delete/{id}", produces = "text/plain", consumes = "application/json")
	public String deleteByIdx(@PathVariable Integer id) {
		// db logic to delete by id 
		
		return "Deleted";
	}
	
	@PostMapping(value="/add", produces = "text/plain", consumes = "application/json")
	public ResponseEntity<String> addEmployee(@RequestBody Employee emp){
		// logic to insert into db
		System.out.println(emp.toString());
		
		String body = "Added Successfully";
		
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/update", produces="text/plain", consumes="application/json")
	public String updateEmp(@RequestBody Employee emp) {
		// logic to update 
		System.out.println(emp);
		
		return "Update Successfully";
	}
	
	@GetMapping(value = "/Employee", produces = "application/json")
	public Employee getEmployee() {
		// logic to get record from db
		Employee emp = new Employee(132, "john", 4252.22);
		return emp;
	}

	@GetMapping(value = "/Employees", produces = "application/json")
	public List<Employee> getEmployees() {
		
		// logic to get records from db
		Employee c1 = new Employee(1, "john", 542.5);
		Employee c2 = new Employee(2, "smith", 6532.31);
		Employee c3 = new Employee(3, "david", 1245.12);
		List<Employee> Employees = Arrays.asList(c1, c2, c3);
		return Employees;
	}

}
