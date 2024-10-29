package com.mrsit.service;

import java.util.List;

import com.mrsit.entity.Customer;

public interface CustomerService {
	
	
	public String insetRecord(Customer cust);
	
	public String deleteById(Integer id);
	
	public List<Customer> getAllCustomer();
	
	public Customer getById(Integer id);

}
