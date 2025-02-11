package com.mrsit.Report_API_Project.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchInputs {
	
	private String courseCategory;
	private String trainingMode;
	private String facultyName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startsOn;
	
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public String getTrainingMode() {
		return trainingMode;
	}
	public void setTrainingMode(String trainingMode) {
		this.trainingMode = trainingMode;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public LocalDateTime getStartsOn() {
		return startsOn;
	}
	public void setStartsOn(LocalDateTime startsOn) {
		this.startsOn = startsOn;
	}
	
	
	@Override
	public String toString() {
		return "SearchInputs [courseCategory=" + courseCategory + ", trainingMode=" + trainingMode + ", facultyName="
				+ facultyName + ", startsOn=" + startsOn + "]";
	}

}