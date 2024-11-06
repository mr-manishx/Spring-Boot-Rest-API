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

import com.mrsit.binding.Job;
import com.mrsit.service.JobService;

@RestController
public class JobController {
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}
	
	@PostMapping(value="/job", produces="text/plain", consumes="application/json")
	public ResponseEntity<String> addNewJob(@RequestBody Job job){
		
		String status = jobService.addJob(job);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/job")
	public ResponseEntity<Job> getJobById(@RequestParam("id") Integer id){
		Job job = jobService.getJobById(id);
		if(job != null) {
			return new ResponseEntity<>(job, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value="/jobs")
	public ResponseEntity<List<Job>> getAllJob(){
		List<Job> allJob = jobService.getAllJobs();
		if(allJob.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(allJob, HttpStatus.OK);
	}
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Integer id, @RequestBody Job job){
		String status = jobService.updateById(id, job);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	@DeleteMapping("/id/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable("id") Integer id){
		String status = jobService.deleteById(id);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
