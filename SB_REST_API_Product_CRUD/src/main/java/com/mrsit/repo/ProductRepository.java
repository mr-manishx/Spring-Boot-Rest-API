package com.mrsit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrsit.binding.Product;

public interface ProductRepository extends JpaRepository<Product, Serializable>{

}
