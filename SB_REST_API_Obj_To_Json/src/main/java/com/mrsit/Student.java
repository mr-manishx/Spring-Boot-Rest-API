package com.mrsit;

public class Student {

	private Integer rollNo;
	private String name;
	private String gender;
	private Integer fees;
	
	

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Integer rollNo, String name, String gender, Integer fees) {
		this.rollNo = rollNo;
		this.name = name;
		this.gender = gender;
		this.fees = fees;
	}

	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getFees() {
		return fees;
	}

	public void setFees(Integer fees) {
		this.fees = fees;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", gender=" + gender + ", fees=" + fees + "]";
	}
	
}
