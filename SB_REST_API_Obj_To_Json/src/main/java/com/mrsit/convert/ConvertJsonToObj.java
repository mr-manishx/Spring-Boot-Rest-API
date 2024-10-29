package com.mrsit.convert;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrsit.Student;

public class ConvertJsonToObj {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		// TODO Auto-generated method stub
		
		File f = new File("student.json");
		
		ObjectMapper mapper = new ObjectMapper();
		Student std = mapper.readValue(f, Student.class);
		
		System.out.print(std);
	}

}
