package com.mrsit.convert;

import java.io.File;
//import java.util.Arrays;
//import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrsit.Student;

public class ConvertObjToJson {

	public static void main(String[] args1) throws Exception {
		
		Student s1 = new Student(102, "Martin", "Male", 3000);
//		Student s2 = new Student(103, "Alex", "Male", 2500);
//		Student s3 = new Student(104, "Putin", "Male", 300);
//		Student s4 = new Student(105, "Alice", "Female", 4000);
//		
//		List<Student> std = Arrays.asList(s1, s2, s3, s4);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("student.json"), s1);
		
		System.out.println("Completed..");
	}
}
