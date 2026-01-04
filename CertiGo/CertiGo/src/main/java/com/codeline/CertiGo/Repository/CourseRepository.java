package com.codeline.CertiGo.Repositories;

import com.codeline.CertiGo.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
