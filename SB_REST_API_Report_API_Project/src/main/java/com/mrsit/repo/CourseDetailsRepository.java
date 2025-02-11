package com.mrsit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mrsit.entity.CourseDetails;
import com.mrsit.model.SearchInputs;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetails, Integer>{

	
	// query to get distinct category from database
	@Query("SELECT DISTINCT c.courseCategory FROM course_details c")
	List<String> findDistinctCategories();
	
	

    // Query to get distinct training modes
    @Query("SELECT DISTINCT c.trainingMode FROM course_details c")
    List<String> findDistinctTrainingModes();

    // Query to get distinct faculties
    @Query("SELECT DISTINCT c.faculty FROM course_details c")
    List<String> findDistinctFaculties();

    // Dynamic query to filter courses based on inputs
    @Query("SELECT c FROM course_details c " +
           "WHERE (:courseCategory IS NULL OR c.courseCategory = :courseCategory) " +
           "AND (:trainingMode IS NULL OR c.trainingMode = :trainingMode) " +
           "AND (:facultyName IS NULL OR c.facultyName = :facultyName)")
    List<CourseDetails> findCoursesByFilters(
    		@Param("categorycourseCategory")String categorycourseCategory, 
    		@Param("trainingMode")String trainingMode, 
    		@Param("facultyName")String facultyName
    );



//	List<CourseDetails> findCoursesByFilters(SearchInputs inputs);
    @Query("SELECT c FROM CourseDetails c WHERE c.courseName LIKE %:#{#searchInputs.courseName}% AND c.duration = :#{#searchInputs.duration}")
    List<CourseDetails> findCoursesByFilters(@Param("searchInputs") SearchInputs searchInputs);


}
