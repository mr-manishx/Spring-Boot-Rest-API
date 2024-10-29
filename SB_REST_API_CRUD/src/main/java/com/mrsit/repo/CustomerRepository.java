package com.mrsit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrsit.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
