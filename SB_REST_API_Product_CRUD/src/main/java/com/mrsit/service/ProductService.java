package com.mrsit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Product;

@Service
public interface ProductService {

	public String upsert(Product prod);
	
	public Product getRecordById(Integer prodId);
	
	public String deleteItemById(Integer prodId);
	
	public List<Product> getAllProductInfo();
}
