package com.mrsit.service;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Row;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mrsit.entity.CourseDetails;
import com.mrsit.model.SearchInputs;
import com.mrsit.model.SearchResults;
import com.mrsit.repo.CourseDetailsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseServiceImp implements ICourseService {

    @Autowired
    private CourseDetailsRepository courseRepository;  // Assume this is a JPA repository or similar

//    public CourseServiceImp(CourseDetailsRepository courseRepository) {
//		this.courseRepository = courseRepository;
//	}

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
       
    	    // Assuming the repository returns List<CourseDetails>
    	    List<CourseDetails> filteredCourses = courseRepository.findCoursesByFilters(
    	            inputs.getCourseCategory(),
    	            inputs.getTrainingMode(),
    	            inputs.getFacultyName()
    	    );

    	    // Using a stream to map CourseDetails to SearchResults
    	    return filteredCourses.stream()
    	                          .map(courseDetails -> new SearchResults())  // Ensure this line is correct
    	                          .collect(Collectors.toList());

    }
 
    @Override
    public void generatePdfReport(SearchInputs inputs, HttpServletResponse response) {
        // Fetch filtered courses
        List<CourseDetails> filteredCourses = courseRepository.findCoursesByFilters(inputs);

        // Here, you would use a PDF generation library like iText or Apache PDFBox
        // Example: using iText to generate a PDF
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"courses_report.pdf\"");

            // Generate PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Courses Report"));
            
            // Add course details to the PDF
            for (CourseDetails course : filteredCourses) {
                document.add(new Paragraph(course.toString()));
            }
            document.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateExcelReport(SearchInputs inputs, HttpServletResponse response) {
        // Fetch filtered courses
    	Workbook workbook = new XSSFWorkbook();
        Sheet sheet = (Sheet) workbook.createSheet("Courses Report");

        // Fetch the data from the repository
        List<CourseDetails> courses = courseRepository.findCoursesByFilters(
                inputs.getCourseCategory(),
                inputs.getTrainingMode(),
                inputs.getFacultyName()
        );

        // Dynamically determine headers using reflection (based on the CourseDetails class)
        java.lang.reflect.Field[] fields = CourseDetails.class.getDeclaredFields();
        
        // Create header row using the dynamic field names
        Row headerRow = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(0);
        for (int i = 0; i < fields.length; i++) {
            Cell cell = (Cell) ((org.apache.poi.ss.usermodel.Row) headerRow).createCell(i);
            ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(fields[i].getName()); // Use field names as headers
        }

        // Populate data rows dynamically
        int rowNum = 1; // Start after the header row
        for (CourseDetails course : courses) {
            Row row = (Row) ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(rowNum++);

            // Iterate through each field of the CourseDetails object dynamically
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true); // Ensure the field is accessible
                try {
                    Object value = fields[i].get(course); // Get the value dynamically
                    Cell cell = (Cell) ((org.apache.poi.ss.usermodel.Row) row).createCell(i);
                    ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(value != null ? value.toString() : ""); // Set cell value
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Set the response properties for the Excel file download
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Courses_Report.xlsx");

        // Write the Excel file to the response output stream
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void generatePdfReportAllData(HttpServletResponse response) throws Exception{
		// get all the records from the table
		
		List<CourseDetails> list = courseRepository.findAll();
		// copy List<CourseDetails> to List<SearchResults>
		List<SearchResults> listResults = new ArrayList();
		list.forEach(course->{
			SearchResults result = new SearchResults();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
		
		// create Document obj(openPdf)
		Document document = new Document(PageSize.A4);
		// get PdfWriter to write to the document and response obj
		PdfWriter.getInstance(document, response.getOutputStream());
		//open the document
		document.open();
		// Define Font for the Paragraph
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setSize(30);
		font.setColor(Color.CYAN);
		
		// create the paragraph having content and above font style
		Paragraph para = new Paragraph("Search Report of Courses", font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		//add paragraph to document
		document.add(para);
		
		// display search results as the pdf table
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(70);
		table.setWidths(new float[]{3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f});
		table.setSpacingBefore(2.0f);
		
		// prepare heading row cells in the pdf table
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellFont.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("courseID", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("courseName", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Category", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("FacultyName", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Location", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Fee", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Course Staus", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("TrainingMode", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("adminContant", cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("StartDate", cellFont));
		table.addCell(cell);
		
		// add data cells to pdftable
		listResults.forEach(result->{
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(result.getCourseName());
			table.addCell(result.getCourseCategory());
			table.addCell(result.getFacultyName());
			table.addCell(result.getLocation());
			table.addCell(String.valueOf(result.getFee()));
			table.addCell(result.getCourseStatus());
			table.addCell(result.getTrainingMode());
			table.addCell(String.valueOf(result.getAdminContact()));
			table.addCell(result.getStartDate().toString());
			
		});
		
		// add table to document
		document.add(table);
		// close the document
		document.close();
	}

	@Override
	public void generateExcelReportAllData(HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		List<CourseDetails> list = courseRepository.findAll();
		// copy List<CourseDetails> to List<SearchResults>
		List<SearchResults> listResults = new ArrayList();
		list.forEach(course->{
			SearchResults result = new SearchResults();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
		
		// create ExcelWorkBook(apache poi api) 
		HSSFWorkbook workbook = new HSSFWorkbook();
		// create sheet in the work book
		HSSFSheet sheet1 = workbook.createSheet("CourseDetails");
		// create heading row in sheet1
		HSSFRow headerRow = sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseId");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("Location");
		headerRow.createCell(3).setCellValue("CourseCategory");
		headerRow.createCell(4).setCellValue("FacultyName");
		headerRow.createCell(5).setCellValue("fee");
		headerRow.createCell(6).setCellValue("adminContact");
		headerRow.createCell(7).setCellValue("trainingMode");
		headerRow.createCell(8).setCellValue("startDate");
		headerRow.createCell(9).setCellValue("CourseStatus");
		
		// add data rows to the sheet
		int i = 1;
		for(SearchResults result:listResults) {
			HSSFRow dataRow = sheet1.createRow(i);
			dataRow.createCell(0).setCellValue(result.getCourseId());
			dataRow.createCell(1).setCellValue(result.getCourseName());
			dataRow.createCell(2).setCellValue(result.getLocation());
			dataRow.createCell(3).setCellValue(result.getCourseCategory());
			dataRow.createCell(4).setCellValue(result.getFacultyName());
			dataRow.createCell(5).setCellValue(result.getFee());
			dataRow.createCell(6).setCellValue(result.getAdminContact());
			dataRow.createCell(7).setCellValue(result.getTrainingMode());
			dataRow.createCell(8).setCellValue(result.getStartDate());
			dataRow.createCell(9).setCellValue(result.getCourseStatus());
			i++;
			
		}
		
		// get OutputStream pointing to response obj
		
		ServletOutputStream outputStream = response.getOutputStream();
		// write the excel work book data response object using the above stream
		workbook.write(outputStream);
		// outputStream.close();
		workbook.close();
		
	}
}
