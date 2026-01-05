package com.codeline.CertiGo.Repositories;

import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("SELECT l FROM Lesson l WHERE l.id =:id AND l.isActive = true")
    Lesson getLessonById(Integer id);

    @Query("SELECT l FROM Lesson l WHERE l.isActive=true AND l.id IN (:id) ")
    List<Lesson> getLessonById(List<Integer> id);
}


