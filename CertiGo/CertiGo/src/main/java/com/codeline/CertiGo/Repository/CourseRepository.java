package com.codeline.CertiGo.Repositories;

import com.codeline.CertiGo.Entity.Company;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query("SELECT c FROM Course c WHERE c.orderId=:id and c.isActive=true")
    Course getCourseById(Integer id);

    @Query("SELECT c FROM Course c WHERE c.isActive=true AND c.id IN (:id) ")
    List<Course> getCourseById(List<Integer> id);

}

