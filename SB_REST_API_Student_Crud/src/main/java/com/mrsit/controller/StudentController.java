package com.mrsit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrsit.binding.Student;
import com.mrsit.service.StudentService;

@RestController
public class StudentController {
	
	private StudentService stdService;
	
	public StudentController(StudentService stdService) {
		this.stdService = stdService;
	}
	
	@PostMapping(value="/student")
	public ResponseEntity<String> SetUpsert(@RequestBody Student std){
		
		String status = stdService.upsert(std);

		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@GetMapping("/student")
	public ResponseEntity<Student> getStudent(@RequestParam("id") Integer id){
		Student std = stdService.getById(id);
		return new ResponseEntity<>(std, HttpStatus.OK);
	}
	
	@PutMapping(value="/update", produces = "text/plain", consumes = "application/json")
	public ResponseEntity<String> updateStudent(@RequestBody Student std){
		
		String status = stdService.upsert(std);

		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
		
		String status = stdService.deleteById(id);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent(){
		
		List<Student> stdlist = stdService.getAllStudent();
		
		return new ResponseEntity<>(stdlist, HttpStatus.OK);
	}
}
