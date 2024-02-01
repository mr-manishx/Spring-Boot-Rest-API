package com.mrsit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Faculty;

@Service
public interface FacultyService {

	public String upsert(Faculty fact);
	
	public List<Faculty> getAllRecord();
	
	public String deleteRecordById(Integer id);
	
	public Faculty getRecordByid(Integer id);
}
