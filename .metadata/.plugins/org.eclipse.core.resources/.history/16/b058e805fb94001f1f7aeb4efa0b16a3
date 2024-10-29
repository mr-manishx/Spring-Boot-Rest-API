package com.mrsit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mrsit.binding.Product;
import com.mrsit.repo.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {
	
	private ProductRepository prodRepo;
	
	public ProductServiceImp(ProductRepository prodRepo) {
		this.prodRepo = prodRepo;
	}

	@Override
	public String upsert(Product prod) {
		// TODO Auto-generated method stub
		prodRepo.save(prod);
		return "Success";
	}

	@Override
	public Product getRecordById(Integer prodId) {
		// TODO Auto-generated method stub
		Optional<Product> prdId = prodRepo.findById(prodId);
		if(prdId.isPresent()) {
			return prdId.get();
		}
		return null;
	}

	@Override
	public String deleteItemById(Integer prodId) {
		// TODO Auto-generated method stub
		if(prodRepo.existsById(prodId)) {
			prodRepo.deleteById(prodId);
		}
		return "Record Not Found!";
	}

	@Override
	public List<Product> getAllProductInfo() {
		// TODO Auto-generated method stub
		return prodRepo.findAll();
	}

}
