package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    @Query(" SELECT s FROM Student s WHERE s.id=:id AND s.isActive=true")
    Instructor getInstructorById(Integer id);

    @Query("SELECT i FROM Instructor i WHERE i.isActive=true AND i.id IN (:id) ")
    List<Instructor> getInstructorById(List<Integer> id);
}
