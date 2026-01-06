package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer,Integer> {

    @Query(" SELECT answer FROM UserAnswer answer WHERE answer.id=:id AND answer.isActive=true")
    UserAnswer getUserAnswerById(Integer id);

    @Query("SELECT answer FROM UserAnswer answer WHERE answer.isActive=true")
    List<UserAnswer> findUserAnswers();


}
