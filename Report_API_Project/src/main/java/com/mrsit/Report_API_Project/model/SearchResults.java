package com.mrsit.Report_API_Project.model;

import java.time.LocalDateTime;

import com.mrsit.Report_API_Project.entities.CourseDetails;


public class SearchResults {

    private Integer courseId;
    private String courseName;
    private String location;
    private String courseCategory;
    private String facultyName;
    private Double fee;
    private Long adminContact;
    private String trainingMode;
    private LocalDateTime startDate;
    private String courseStatus;

    // Constructor to map from CourseDetails
    public SearchResults(CourseDetails course) {
        this.courseId = course.getCourseId();
        this.courseName = course.getCourseName();
        this.location = course.getLocation();
        this.courseCategory = course.getCourseCategory();
        this.facultyName = course.getFacultyName();
        this.fee = course.getFee();
        this.adminContact = course.getAdminContact();
        this.trainingMode = course.getTrainingMode();
        this.startDate = course.getStartDate();
        this.courseStatus = course.getCourseStatus();
    }


	// Getters and Setters
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Long getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(Long adminContact) {
        this.adminContact = adminContact;
    }

    public String getTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(String trainingMode) {
        this.trainingMode = trainingMode;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    @Override
    public String toString() {
        return "SearchResults [courseId=" + courseId + ", courseName=" + courseName + ", location=" + location + 
                ", courseCategory=" + courseCategory + ", facultyName=" + facultyName + ", fee=" + fee + 
                ", adminContact=" + adminContact + ", trainingMode=" + trainingMode + ", startDate=" + startDate + 
                ", courseStatus=" + courseStatus + "]";
    }
}
