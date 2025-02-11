package com.mrsit.Report_API_Project.service;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
//import com.itextpdf.layout.property.TextAlignment;
//import com.itextpdf.layout.element.Paragraph;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.mrsit.Report_API_Project.entities.CourseDetails;
import com.mrsit.Report_API_Project.model.SearchInputs;
import com.mrsit.Report_API_Project.model.SearchResults;
import com.mrsit.Report_API_Project.repo.CourseDetailsRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseServiceImp implements ICourseService {

	@Autowired
	private CourseDetailsRepository courseRepository; // Assume this is a JPA repository or similar

	public CourseServiceImp(CourseDetailsRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Set<String> showAllCourseCategories() {
		// Fetch distinct categories from the Course entity
		return new HashSet<>(courseRepository.findDistinctCategories());
	}

	@Override
	public Set<String> showAllTrainingModes() {
		// Fetch distinct training modes from the Course entity
		return new HashSet<>(courseRepository.findDistinctTrainingModes());
	}

	@Override
	public Set<String> showAllFaculties() {
		// Fetch distinct faculties from the Course entity
		return new HashSet<>(courseRepository.findDistinctFaculties());
	}

	@Override
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs) {
		List<CourseDetails> filteredCourses = courseRepository.findCoursesByFilters(inputs.getCourseCategory(),
				inputs.getTrainingMode(), inputs.getFacultyName());

		return filteredCourses.stream().map(course -> {
			SearchResults result = new SearchResults(course);
			BeanUtils.copyProperties(course, result);
			return result;
		}).collect(Collectors.toList());
	}

	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse response) throws IOException, DocumentException {
		List<CourseDetails> filteredCourses = courseRepository.findCoursesByFilters(inputs);

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"courses_report.pdf\"");

//		Document document = new Document();
//		PdfWriter.getInstance(document, response.getOutputStream());
//		document.open();
//
//		com.lowagie.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
//		Paragraph title = new Paragraph("Courses Report", titleFont);
//		title.setAlignment(TextAlignment.CENTER);
//		document.add((com.itextpdf.text.Element) title);
//		document.add((com.itextpdf.text.Element) new Paragraph("\n"));
		
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		// Define title font
		com.lowagie.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

		// Create paragraph with title
		Paragraph title = new Paragraph("Courses Report", titleFont);
		title.setAlignment(Paragraph.ALIGN_CENTER); // ✅ Correct method for iText 5

		document.add((com.itextpdf.text.Element) title);
		document.add((com.itextpdf.text.Element) new Paragraph("\n"));

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setWidths(new float[] { 3f, 3f, 3f, 3f, 3f, 3f });

		com.lowagie.text.Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
		PdfPCell cell = new PdfPCell();
		cell.setBorderColor(Color.DARK_GRAY);
		cell.setPadding(5);

		String[] headers = { "Course Name", "Location", "Category", "Faculty", "Fee", "Mode" };
		for (String header : headers) {
			cell.setPhrase(new Phrase(header, headerFont));
			table.addCell(cell);
		}

		for (CourseDetails course : filteredCourses) {
			table.addCell(course.getCourseName());
			table.addCell(course.getLocation());
			table.addCell(course.getCourseCategory());
			table.addCell(course.getFacultyName());
			table.addCell(String.valueOf(course.getFee()));
			table.addCell(course.getTrainingMode());
		}

		document.add((com.itextpdf.text.Element) table);
		document.close();
	}

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse response) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Courses Report");

		List<CourseDetails> courses = courseRepository.findCoursesByFilters(inputs.getCourseCategory(),
				inputs.getTrainingMode(), inputs.getFacultyName());

		String[] headers = { "Course Name", "Location", "Category", "Faculty", "Fee", "Mode" };
		org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			headerRow.createCell(i).setCellValue(headers[i]);
		}

		int rowNum = 1;
		for (CourseDetails course : courses) {
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(course.getCourseName());
			row.createCell(1).setCellValue(course.getLocation());
			row.createCell(2).setCellValue(course.getCourseCategory());
			row.createCell(3).setCellValue(course.getFacultyName());
			row.createCell(4).setCellValue(course.getFee());
			row.createCell(5).setCellValue(course.getTrainingMode());
		}

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=Courses_Report.xlsx");

		workbook.write(response.getOutputStream());
		workbook.close();
	}

	@Override
	public void generatePdfReportAllData(HttpServletResponse response) throws DocumentException {
		List<CourseDetails> list = courseRepository.findAll();
		List<SearchResults> listResults = new ArrayList<>();
		list.forEach(course -> {
			SearchResults result = new SearchResults(course);
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=Courses_Report_All.pdf");

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.open();

		com.lowagie.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLUE);
		Paragraph title = new Paragraph("All Courses Report", titleFont);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add((com.itextpdf.text.Element) title);
		document.add((com.itextpdf.text.Element) new Paragraph("\n"));

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 3f, 3f, 3f, 3f, 3f, 3f });

		String[] headers = { "ID", "Course Name", "Location", "Faculty", "Fee", "Status" };
		for (String header : headers) {
			PdfPCell cell = new PdfPCell(
					new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE)));
			cell.setBackgroundColor(Color.GRAY);
			table.addCell(cell);
		}

		for (SearchResults result : listResults) {
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(result.getCourseName());
			table.addCell(result.getLocation());
			table.addCell(result.getFacultyName());
			table.addCell(String.valueOf(result.getFee()));
			table.addCell(result.getCourseStatus());
		}

		document.add((com.itextpdf.text.Element) table);
		document.close();
	}

	@Override
	public void generateExcelReportAllData(HttpServletResponse response) {
		List<CourseDetails> list = courseRepository.findAll();
		List<SearchResults> listResults = new ArrayList<>();
		list.forEach(course -> {
			SearchResults result = new SearchResults(course);
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Courses Report");

		HSSFRow headerRow = sheet.createRow(0);
		String[] headers = { "CourseId", "Course Name", "Location", "Faculty", "Fee", "Status" };
		for (int i = 0; i < headers.length; i++) {
			headerRow.createCell(i).setCellValue(headers[i]);
		}

		int i = 1;
		for (SearchResults result : listResults) {
			HSSFRow row = sheet.createRow(i++);
			row.createCell(0).setCellValue(result.getCourseId());
			row.createCell(1).setCellValue(result.getCourseName());
			row.createCell(2).setCellValue(result.getLocation());
			row.createCell(3).setCellValue(result.getFacultyName());
			row.createCell(4).setCellValue(result.getFee());
			row.createCell(5).setCellValue(result.getCourseStatus());
		}

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Courses_Report_All.xls");

		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}