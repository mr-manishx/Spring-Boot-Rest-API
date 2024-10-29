package com.mrsit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Student;

@Service
public interface StudentService {
	
	public String upsert(Student std);
	
	public Student getById(Integer id);
	
	public List<Student> getAllStudent();
	
	public String deleteById(Integer id);

}
