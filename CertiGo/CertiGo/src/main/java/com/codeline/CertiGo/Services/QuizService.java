package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOResponse.QuizCreateResponse;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Entity.Quiz;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repositories.CourseRepository;
import com.codeline.CertiGo.Repository.QuizRepository;
import com.codeline.CertiGo.DTOCreateRequest.QuizCreateRequest;
import com.codeline.CertiGo.Repository.QuestionRepository;
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
    CourseRepository courseRepository;

    public List<Quiz> getAllQuizzes() throws CustomException {
        return quizRepository.findAll();
    }
    public QuizCreateResponse saveQuiz(QuizCreateRequest request)throws CustomException{
           Quiz quiz = QuizCreateRequest.convertToQuiz(request);
           quiz.setCreatedAt(new Date());
           quiz.setIsActive(Boolean.TRUE);

        Course course = courseRepository.getCourseById(request.getCoursesId());
                if (Utils.isNotNull(course)) {
                    quiz.setCourse(course);
                }else {
                    throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
                }

                List<Question> question=questionRepository.findAllActiveQuestions(request.getQuestionsId());
                if (Utils.isNotNull(question)|| Utils.isListNotEmpty(question)){
                        quiz.setQuestions(question);
                }else {
                    throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
                }
            return QuizCreateResponse.ConvertToQuizCreateResponse(quizRepository.save(quiz));
    }
        public Quiz updateQuiz(Quiz quiz) throws CustomException {
        Quiz existingQuiz =quizRepository.findById(quiz.getId()).get();
        if (existingQuiz != null && existingQuiz.getIsActive()) {
            quiz.setUpdatedAt(new Date());
            return quizRepository.save(quiz);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteQuiz(Integer id) throws CustomException {
        Quiz existingQuiz = quizRepository.findById(id).get();
        if (existingQuiz != null && existingQuiz.getIsActive()) {
            existingQuiz.setUpdatedAt(new Date());
            existingQuiz.setIsActive(Boolean.FALSE);
            quizRepository.save(existingQuiz);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Quiz getQuizById(Integer id) throws CustomException {
        Quiz existingQuiz = quizRepository.findById(id).get();
        if (existingQuiz != null && existingQuiz.getIsActive()) {
            return existingQuiz;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}