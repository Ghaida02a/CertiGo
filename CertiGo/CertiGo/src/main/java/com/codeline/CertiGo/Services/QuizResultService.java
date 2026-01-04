package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.Repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizResultService {
    @Autowired
    QuizResultRepository quizRepository;
    @Autowired
    QuizResultRepository quizResultRepository;
}
