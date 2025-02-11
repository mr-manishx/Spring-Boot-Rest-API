package com.mrsit.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mrsit.model.SearchInputs;
import com.mrsit.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface ICourseService {
	
	public Set<String> showAllCourseCategories();
	public Set<String> showAllTrainingModes();
	public Set<String> showAllFaculties();
	
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs);
	
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse response);
	
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse response) throws IOException;
	
	public void generatePdfReportAllData(HttpServletResponse response) throws Exception;
	
	public void generateExcelReportAllData(HttpServletResponse response) throws Exception;

}
