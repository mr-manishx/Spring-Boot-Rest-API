package com.mrsit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mrsit.entity.Customer;
import com.mrsit.repo.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {
	
	private CustomerRepository custRepo;
	
	public CustomerServiceImp(CustomerRepository custRepo) {
		this.custRepo = custRepo;
	}
	
	@Override
	public String deleteById(Integer id) {
		
		Optional<Customer> findId = custRepo.findById(id);
		if(findId.isPresent()) {
			custRepo.deleteById(id);
			return "Deleted";
		}
		return "Record not Found!";
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return custRepo.findAll();
	}

	@Override
	public Customer getById(Integer id) {
		// TODO Auto-generated method stub
		return custRepo.findById(id).orElse(null);
	}

	@Override
	public String insetRecord(Customer cust) {
		custRepo.save(cust);
		
		return "Inserted Success";
	}

}
