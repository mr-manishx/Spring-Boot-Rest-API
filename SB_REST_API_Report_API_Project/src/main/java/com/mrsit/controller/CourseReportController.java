package com.mrsit.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrsit.model.SearchInputs;
import com.mrsit.model.SearchResults;
import com.mrsit.service.ICourseService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporting/api")
public class CourseReportController {

	@Autowired
	private ICourseService courseService;
	
	public CourseReportController(ICourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/course")
	public ResponseEntity<?> fetchCourseCategories() {
		try {
			// use service
			Set<String> courseInfo = courseService.showAllCourseCategories();
			return new ResponseEntity<Set<String>>(courseInfo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/training-modes")
	public ResponseEntity<?> fetchTrainingModes(){
		try{
			//use service
			Set<String> trainingModeInfo = courseService.showAllTrainingModes();
			return new ResponseEntity<Set<String>>(trainingModeInfo, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/faculties")
	public ResponseEntity<?> fetchFaculties(){
		try {
			//use service
			Set<String> facultiesInfo = courseService.showAllFaculties();
			return new ResponseEntity<Set<String>>(facultiesInfo, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> fetchCourseByFilters(@RequestBody SearchInputs inputs){
		try {
			List<SearchResults> list = courseService.showCoursesByFilters(inputs);
			return new ResponseEntity<List<SearchResults>>(list, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/pdf-report")
	public void showPdfReport(@RequestBody SearchInputs inputs, HttpServletResponse res) {
		try {
			// set the response content type
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
			// use service
			courseService.generatePdfReport(inputs, res);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/excel-report")
	public void showExcelReport(@RequestBody SearchInputs inputs, HttpServletResponse res) {
		
		try {
			// set the response content type
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
			// use service
			courseService.generateExcelReport(inputs, res);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/all-pdf-report")
	public void showPdfReportAllData(HttpServletResponse res) {
		try {
			// set the response content type
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
			// use service
			courseService.generatePdfReportAllData(res);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/all-excel-report")
	public void showExcelReportAllData(@RequestBody SearchInputs inputs, HttpServletResponse res) {
		
		try {
			// set the response content type
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
			// use service
			courseService.generateExcelReportAllData(res);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}