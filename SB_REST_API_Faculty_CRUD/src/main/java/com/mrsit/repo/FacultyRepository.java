package com.mrsit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mrsit.binding.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Serializable>{

}
