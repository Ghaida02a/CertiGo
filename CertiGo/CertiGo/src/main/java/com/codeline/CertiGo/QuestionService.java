package com.codeline.CertiGo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeline.CertiGo.Repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
}
