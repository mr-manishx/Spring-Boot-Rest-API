package com.mrsit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrsit.entity.Customer;
import com.mrsit.service.CustomerService;

@RestController
public class CustomerController {
	
	private CustomerService custService;
	
	public CustomerController(CustomerService custService) {
		this.custService = custService;
	}
	
	@PostMapping("/customer")
	public ResponseEntity<String> getInsert(@RequestBody Customer cust){
		
//		Customer cust = new Customer("Alexa", "alexa@gmail.com", "Female");
		String status = custService.insetRecord(cust);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@GetMapping("/home")
	public ResponseEntity<String> deleteRecord(){
		
		String status = "Hello i am Developer";
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteRecordById(@PathVariable Integer id){
		
		String status = custService.deleteById(id);
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
