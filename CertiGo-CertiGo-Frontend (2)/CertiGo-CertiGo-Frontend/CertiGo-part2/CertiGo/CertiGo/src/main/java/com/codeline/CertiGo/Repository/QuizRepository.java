package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Lesson;
import com.codeline.CertiGo.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query("SELECT q FROM Quiz q WHERE q.id =:id AND q.isActive = true")
    Quiz getQuizById(Integer id);

    @Query("SELECT q FROM Quiz q WHERE q.isActive=true AND q.id IN (:id) ")
    List<Quiz> getQuizById(List<Integer> id);
}

