package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Entity.Quiz;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repositories.CourseRepository;
import com.codeline.CertiGo.Repositories.QuizRepository;
import com.codeline.CertiGo.DTOCreateRequest.QuizCreateRequest;
import com.codeline.CertiGo.DTOCreateResponse.QuizCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizResultRepository quizResultRepository;

    public List<Quiz> getAllQuizzes() throws CustomException {
        return quizRepository.findAll();
    }
    public QuizCreateResponse saveQuiz(QuizCreateRequest request)throws CustomException{
           Quiz quiz = QuizCreateRequest.ConvertToQuize(request);
           quiz.setCreatedAt(new Date());
           quiz.setIsActive(Boolean.TRUE);

        Course course = CourseRepository.getCourseById(request.getCourseId());
                if (Utils.isNotNull(course)) {
                    Quiz.setCourse(course);
                }else {
                    throw new Exception(Constants.QUIZ_CREATE_REQUEST_COURSE_ID_NOT_VALID);
                }

                List<Question> question=   questionRepository.getQuestionById(request.getQuestionsId());
                if (Utils.isNotNull(question)|| Utils.isListNotEmpty(question)){
                        Quiz.setQuestion(question);
                }else {
                    throw new Exception(Constants.QUIZ_CREATE_REQUEST_QUESTIONS_ID_NOT_VALID);
                }
            return QuizCreateResponse.ConvertToQuizCreateResponse(quizRepository.save(quiz));
    }
        public Quiz updateQuiz(Quiz quiz) throws CustomException {
        Quiz existingQuiz =QuizRepository.findById(Quiz.getId()).get();
        if (existingQuiz != null && existingQuiz.getIsActive()) {
            Quiz.setUpdatedAt(new Date());
            return QuizRepository.save(Quiz);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteQuiz(Integer id) throws CustomException {
        Quiz existingQuiz = QuizRepository.findById(id).get();
        if (existingQuiz != null && existingQuiz.getIsActive()) {
            existingQuiz.setUpdatedAt(new Date());
            existingQuiz.setIsActive(Boolean.FALSE);
            QuizRepository.save(existingQuiz);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public Quiz getQuizById(Integer id) throws CustomException {
        Quiz existingQuiz = QuizRepository.findById(id).get();
        if (existingQuiz != null && existingQuiz.getIsActive()) {
            return existingQuiz;
        } else {
            throw new Exception("BAD REQUEST");
        }
    }
}