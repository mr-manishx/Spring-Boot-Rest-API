package com.mrsit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrsit.binding.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{

	
}
