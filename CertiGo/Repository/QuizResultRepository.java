package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {
    @Query("SELECT qr FROM QuizResult qr WHERE qr.id =:id AND qr.isActive = true")
    QuizResult getQuizResultById(Integer id);

    @Query("SELECT qr FROM QuizResult qr WHERE qr.isActive = true")
    List<QuizResult> findAllActiveQuizResults();
}
