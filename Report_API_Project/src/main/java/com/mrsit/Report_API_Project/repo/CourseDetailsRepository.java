package com.mrsit.Report_API_Project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mrsit.Report_API_Project.entities.CourseDetails;
import com.mrsit.Report_API_Project.model.SearchInputs;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetails, Integer> {

    // Query to get distinct categories
    @Query("SELECT DISTINCT c.courseCategory FROM CourseDetails c")
    List<String> findDistinctCategories();

    // Query to get distinct training modes
    @Query("SELECT DISTINCT c.trainingMode FROM CourseDetails c")
    List<String> findDistinctTrainingModes();

    // Query to get distinct faculties
    @Query("SELECT DISTINCT c.facultyName FROM CourseDetails c") // Fixed field name
    List<String> findDistinctFaculties();

    // Dynamic query to filter courses based on inputs
    @Query("SELECT c FROM CourseDetails c " +
           "WHERE (:courseCategory IS NULL OR c.courseCategory = :courseCategory) " +
           "AND (:trainingMode IS NULL OR c.trainingMode = :trainingMode) " +
           "AND (:facultyName IS NULL OR c.facultyName = :facultyName)")
    List<CourseDetails> findCoursesByFilters(
        @Param("courseCategory") String courseCategory,  // Fixed param name
        @Param("trainingMode") String trainingMode, 
        @Param("facultyName") String facultyName
    );

    // Query using SearchInputs model
//    @Query("SELECT c FROM CourseDetails c WHERE c.courseName LIKE %:#{#searchInputs.courseName}%")
//    List<CourseDetails> findCoursesByFilters(@Param("searchInputs") SearchInputs searchInputs);
    
    @Query("SELECT c FROM CourseDetails c " +
    	       "WHERE (:#{#searchInputs.courseCategory} IS NULL OR c.courseCategory = :#{#searchInputs.courseCategory}) " +
    	       "AND (:#{#searchInputs.trainingMode} IS NULL OR c.trainingMode = :#{#searchInputs.trainingMode}) " +
    	       "AND (:#{#searchInputs.facultyName} IS NULL OR c.facultyName = :#{#searchInputs.facultyName}) " +
    	       "AND (:#{#searchInputs.startsOn} IS NULL OR c.startDate >= :#{#searchInputs.startsOn})")
    	List<CourseDetails> findCoursesByFilters(@Param("searchInputs") SearchInputs searchInputs);
}

