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

import com.mrsit.binding.Faculty;
import com.mrsit.service.FacultyService;

@RestController
public class FacultyController {
	
	
	private FacultyService factService;
	
	public FacultyController(FacultyService factService) {
		this.factService = factService;
	}
	
	@PostMapping(value="/faculty", produces="text/plain", consumes="application/json")
	public ResponseEntity<String> useUpsert(@RequestBody Faculty fact){
		String status = factService.upsert(fact);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/factinfo", consumes="application/json")
	public ResponseEntity<Faculty> getRecordById(@RequestParam("factid") Integer id){
		Faculty factInfo = factService.getRecordByid(id);
		return new ResponseEntity<>(factInfo, HttpStatus.OK);
	}
	
	@GetMapping(value="/faculties", consumes="application/json")
	public ResponseEntity<List<Faculty>> getAllRecord(){
		List<Faculty> factList = factService.getAllRecord();
		return new ResponseEntity<>(factList, HttpStatus.OK);
	}
	@DeleteMapping(value="/delete/{id}", produces="text/plain", consumes="application/json")
	public ResponseEntity<String> deleteRecordById(@PathVariable Integer id){
		String status = factService.deleteRecordById(id);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@PutMapping(value="/update", produces="text/plain", consumes="application/json")
	public ResponseEntity<String> updateUpsert(@RequestBody Faculty fact){
		String status = factService.upsert(fact);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
