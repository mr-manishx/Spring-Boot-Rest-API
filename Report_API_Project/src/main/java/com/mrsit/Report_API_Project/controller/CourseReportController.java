package com.mrsit.Report_API_Project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.log.LoggerFactory;
import com.mrsit.Report_API_Project.model.SearchInputs;
import com.mrsit.Report_API_Project.service.ICourseService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporting/api")
public class CourseReportController {

	
	private static final com.itextpdf.text.log.Logger logger = LoggerFactory.getLogger(CourseReportController.class);
	
	private final ICourseService courseService;

	public CourseReportController(ICourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/course")
	public ResponseEntity<?> fetchCourseCategories() {
		try {
			Set<String> courseInfo = courseService.showAllCourseCategories();
			return ResponseEntity.ok(courseInfo);
		} catch (Exception e) {
			logger.error("Error fetching course categories", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/training-modes")
	public ResponseEntity<?> fetchTrainingModes() {
		try {
			Set<String> trainingModeInfo = courseService.showAllTrainingModes();
			return ResponseEntity.ok(trainingModeInfo);
		} catch (Exception e) {
			logger.error("Error fetching training modes", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/faculties")
	public ResponseEntity<?> fetchFaculties() {
		try {
			Set<String> facultiesInfo = courseService.showAllFaculties();
			return ResponseEntity.ok(facultiesInfo);
		} catch (Exception e) {
			logger.error("Error fetching faculties", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/search")
	public ResponseEntity<?> fetchCourseByFilters(@RequestBody SearchInputs inputs) {
		try {
			List<com.mrsit.Report_API_Project.model.SearchResults> list = courseService.showCoursesByFilters(inputs);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			logger.error("Error searching courses", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/pdf-report")
	public void showPdfReport(@RequestBody SearchInputs inputs, HttpServletResponse res) {
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attachment; filename=\"courses.pdf\"");
			res.setHeader("Pragma", "no-cache");
	        res.setHeader("Expires", "0");
	        ServletOutputStream out = res.getOutputStream();
			courseService.generatePdfReport(inputs, res);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("Error generating PDF report", e);
		}
	}

	@PostMapping("/excel-report")
	public void showExcelReport(@RequestBody SearchInputs inputs, HttpServletResponse res) {
		try {
			res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

//			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attachment; filename=\"courses.xls\"");
			courseService.generateExcelReport(inputs, res);
		} catch (Exception e) {
			logger.error("Error generating Excel report", e);
		}
	}

	@PostMapping("/all-pdf-report")
	public void showPdfReportAllData(HttpServletResponse res) {
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attachment; filename=\"courses.pdf\"");
			courseService.generatePdfReportAllData(res);
		} catch (Exception e) {
			logger.error("Error generating all PDF report", e);
		}
	}

	@PostMapping("/all-excel-report")
	public void showExcelReportAllData(HttpServletResponse res) {
		try {
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-Disposition", "attachment; filename=\"courses.xls\"");
			courseService.generateExcelReportAllData(res);
		} catch (Exception e) {
			logger.error("Error generating all Excel report", e);
		}
	}
}
