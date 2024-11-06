package com.mrsit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Job;

@Service
public interface JobService {

	public String addJob(Job job);
	
	public Job getJobById(Integer id);
	
	public List<Job> getAllJobs();
	
	public String updateById(Integer id, Job job);
	
	public String deleteById(Integer id);
}
