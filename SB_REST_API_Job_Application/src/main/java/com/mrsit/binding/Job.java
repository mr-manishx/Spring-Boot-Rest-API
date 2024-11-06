package com.mrsit.binding;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job_tbl")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobId;
	private String tittle;
	private Double miniSalary;
	private Double maxSalary;
	private String location;
	
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public Double getMiniSalary() {
		return miniSalary;
	}
	public void setMiniSalary(Double miniSalary) {
		this.miniSalary = miniSalary;
	}
	public Double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", tittle=" + tittle + ", miniSalary=" + miniSalary + ", maxSalary=" + maxSalary
				+ ", location=" + location + "]";
	}
}
