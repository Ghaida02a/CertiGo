package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
