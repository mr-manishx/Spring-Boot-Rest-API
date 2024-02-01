package com.mrsit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrsit.binding.Product;
import com.mrsit.service.ProductService;

@RestController
public class ProductController {
	
	private ProductService prodService;
	
	public ProductController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	@PostMapping(value="/product", produces="text/plain", consumes="application/json")
	public ResponseEntity<String> upsertUse(@RequestBody Product prod){
		
		String status = prodService.upsert(prod);
		
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/item", produces="application/json")
	public ResponseEntity<Product> getRecordUsingId(@RequestParam("prodId") Integer prodId){
		
		Product status = prodService.getRecordById(prodId);
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{pid}", produces="text/plain", consumes="application/json")
	public ResponseEntity<String> deleteUsingId(@PathVariable Integer pid){
		
		String status = prodService.deleteItemById(pid);
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	@GetMapping(value="/items", produces="application/json")
	public ResponseEntity<List<Product>> getAllItem(){
		List<Product> prodList = prodService.getAllProductInfo();
		return new ResponseEntity<>(prodList, HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateItem(@RequestBody Product prod){
		String status = prodService.upsert(prod);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
