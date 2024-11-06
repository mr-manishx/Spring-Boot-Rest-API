package com.mrsit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Job;
import com.mrsit.repo.JobRepository;

@Service
public class JobServiceImp implements JobService {
	
	private JobRepository jobRepo;
	
	public JobServiceImp(JobRepository jobRepo) {
		this.jobRepo = jobRepo;
	}

	@Override
	public String addJob(Job job) {
		// TODO Auto-generated method stub
		jobRepo.save(job);
		return "Job Added Successfully";
	}

	@Override
	public Job getJobById(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Job> findId = jobRepo.findById(id);
		if(findId.isPresent()) {
			return findId.get();
		}
		return null;
	}

	@Override
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		List<Job> joblist = jobRepo.findAll();
		if(joblist.isEmpty()) {
			return null;
		}
		return jobRepo.findAll();
	}

	@Override
	public String updateById(Integer id, Job job) {
		// TODO Auto-generated method stub
		Optional<Job> isValid = jobRepo.findById(id);
		if(isValid.isPresent()) {
			Job existingJob = isValid.get();
			existingJob.setLocation(job.getLocation());
			existingJob.setMaxSalary(job.getMaxSalary());
			existingJob.setMiniSalary(job.getMiniSalary());
			existingJob.setTittle(job.getTittle());
			jobRepo.save(existingJob);

			return "Job Updated successfully";
		}
		return "Job Id Not Found!";
	}

	@Override
	public String deleteById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Job> isTrue = jobRepo.findById(id);
		if(isTrue.isPresent()) {
			jobRepo.deleteById(id);
			return "Deleted Successfully";
		}
		return "Not Record Found!";
	}

}
