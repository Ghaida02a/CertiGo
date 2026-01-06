package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    @Query("SELECT e FROM Enrollment e WHERE e.id =:id AND e.isActive = true")
    Enrollment getEnrollmentById(Integer id);

    @Query("SELECT e FROM Enrollment e WHERE e.isActive = true")
    List<Enrollment> findAllActiveEnrollments();

    @Query("SELECT e FROM Enrollment e WHERE e.isActive=true AND e.id IN (:id) ")
    List<Enrollment> getEnrollmentById(List<Integer> id);
}
