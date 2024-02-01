/**
 * 
 */
package com.mrsit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Faculty;
import com.mrsit.repo.FacultyRepository;

/**
 * 
 */
@Service
public class FacultyServiceImp implements FacultyService {
	
	private FacultyRepository factRepo;
	
	public FacultyServiceImp(FacultyRepository factRepo) {
		this.factRepo = factRepo;
	}

	@Override
	public String upsert(Faculty fact) {
		// TODO Auto-generated method stub
		factRepo.save(fact);
		return "Successfully";
	}

	@Override
	public List<Faculty> getAllRecord() {
		// TODO Auto-generated method stub
		return factRepo.findAll();
	}

	@Override
	public String deleteRecordById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Faculty> factId = factRepo.findById(id);
		if(factId.isPresent()) {
			factRepo.deleteById(id);
			return "Deleted Success";
		}
		return "Record not found!";
	}

	@Override
	public Faculty getRecordByid(Integer id) {
		// TODO Auto-generated method stub
		Optional<Faculty> factInfo = factRepo.findById(id);
		if(factInfo.isPresent()) {
			return factInfo.get();
		}
		return null;
	}

}
