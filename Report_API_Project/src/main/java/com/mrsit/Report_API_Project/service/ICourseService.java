package com.mrsit.Report_API_Project.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.itextpdf.text.DocumentException;
import com.mrsit.Report_API_Project.entities.CourseDetails;
import com.mrsit.Report_API_Project.model.SearchInputs;
import com.mrsit.Report_API_Project.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourseService {
	
	public String saveNewCourse(CourseDetails course);

    Set<String> showAllCourseCategories();  // Consider using List<String> if ordering matters
    Set<String> showAllTrainingModes();
    Set<String> showAllFaculties();

    List<SearchResults> showCoursesByFilters(SearchInputs inputs);

    void generatePdfReport(SearchInputs inputs, HttpServletResponse response) throws IOException, DocumentException;

    void generateExcelReport(SearchInputs inputs, HttpServletResponse response)throws IOException;

    void generatePdfReportAllData(HttpServletResponse response)throws IOException, DocumentException;

    void generateExcelReportAllData(HttpServletResponse response)throws IOException;
}
