package com.mrsit.Report_API_Project.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails {

    @Id
    @GeneratedValue
    private Integer courseId;

    @Column(length = 30)
    private String courseName;

    @Column(length = 30)
    private String location;

    @Column(length = 30)
    private String courseCategory;

    @Column(length = 30)
    private String facultyName;

    @Column(length = 30)
    private Double fee;

    @Column(length = 30)
    private String adminName;

    @Column(length = 30)
    private Long adminContact;

    @Column(length = 30)
    private String trainingMode;

    @Column(length = 30)
    private LocalDateTime startDate;

    @Column(length = 10)
    private String courseStatus;

    @CreationTimestamp
    @Column(insertable = true, updatable = false)
    private LocalDateTime creationDate;

    @CreationTimestamp
    @Column(insertable = false, updatable = true)
    private LocalDateTime updateDate;
    
    public CourseDetails() {
    }

	public CourseDetails(Integer courseId, String courseName, String location, String courseCategory,
			String facultyName, Double fee, String adminName, Long adminContact, String trainingMode,
			LocalDateTime startDate, String courseStatus, LocalDateTime creationDate, LocalDateTime updateDate) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.location = location;
		this.courseCategory = courseCategory;
		this.facultyName = facultyName;
		this.fee = fee;
		this.adminName = adminName;
		this.adminContact = adminContact;
		this.trainingMode = trainingMode;
		this.startDate = startDate;
		this.courseStatus = courseStatus;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

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

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
    
    
    
}


