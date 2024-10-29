package com.mrsit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Student;
import com.mrsit.repo.StudentRepository;

@Service
public class StudentServiceImp implements StudentService {
	
	private StudentRepository studRepo;
	
	public StudentServiceImp(StudentRepository studRepo) {
		this.studRepo = studRepo;
	}

	@Override
	public String upsert(Student std) {
		// TODO Auto-generated method stub
		
		studRepo.save(std);
		
		return "Success";
	}

	@Override
	public Student getById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Student> std = studRepo.findById(id);
		if(std.isPresent()) {
			return std.get();
		}
		return null;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> stdList = studRepo.findAll();
		return stdList;
	}

	@Override
	public String deleteById(Integer id) {
		// TODO Auto-generated method stub
		if(studRepo.existsById(id)) {
			studRepo.deleteById(id);
			return "Record Deleted";
		}
//		Optional<Student> stdId = studRepo.findById(id);
//		if(stdId.isPresent()) {
//			studRepo.deleteById(id);
//			return "Delete Record";
//		}
		return "Record Not Found!";
	}

}
