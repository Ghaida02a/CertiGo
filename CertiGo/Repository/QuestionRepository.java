package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q FROM Question q WHERE q.id =:id AND q.isActive = true")
    Question getQuestionById(Integer id);

    @Query("SELECT q FROM Question q WHERE q.isActive = true")
    List<Question> findAllActiveQuestions(List<Integer> questionsId);
}
