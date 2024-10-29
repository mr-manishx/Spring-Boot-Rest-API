package com.mrsit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrsit.binding.Student;

public interface StudentRepository extends JpaRepository<Student, Serializable>{

}
