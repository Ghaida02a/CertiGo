package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.QuizResultCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.QuizResultResponse;
import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Repository.QuizResultRepository;
import com.codeline.CertiGo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.codeline.CertiGo.Repositories.QuizRepository;
import com.codeline.CertiGo.Repositories.CourseRepository;


@Service
public class QuizResultService {

    @Autowired
     QuizResultRepository quizResultRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
     QuizRepository quizRepository;

    public QuizResultResponse saveQuizResult(QuizResultCreateRequestDTO request) throws CustomException {

        QuizResult quizResult = new QuizResult();
        quizResult.setScore(request.getScore());
        quizResult.setIsPassed(request.getIsPassed());
        quizResult.setIsActive(Boolean.TRUE);
        quizResult.setCreatedAt(new Date());

        // user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(Constants.USER_ID_NOT_VALID, 404));
        quizResult.setUser(user);

        // quiz
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new CustomException(Constants.QUIZ_ID_NOT_VALID, 404));
        quizResult.setQuiz(quiz);

        // course
        Course course = CourseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CustomException(Constants.COURSE_ID_NOT_VALID, 404));
        quizResult.setCourse(course);

        // user answers
        //quizResult.setUserAnswers( QuizResultCreateRequestDTO.convertToQuizResult(request.getUserAnswers()) );
        if (request.getUserAnswers() != null) {
            request.getUserAnswers().forEach(answer -> {
                answer.setQuizResult(quizResult);
            });
            quizResult.setUserAnswers(request.getUserAnswers());
        }

        QuizResult savedResult = quizResultRepository.save(quizResult);

        return QuizResultResponse.fromEntity(savedResult);
    }
    // GET BY ID
    public QuizResultResponse getQuizResultById(Integer id) throws CustomException {

        QuizResult existingResult = quizResultRepository.findById(id)
                .orElseThrow(() -> new CustomException("QuizResult not found", 404));

        if (Boolean.TRUE.equals(existingResult.getIsActive())) {
            return QuizResultResponse.fromEntity(existingResult);
        } else {
            throw new CustomException("QuizResult not active", 400);
        }
    }

    //GET ALL
    public List<QuizResultResponse> getAllQuizResults() {

        List<QuizResult> results = quizResultRepository.findAllActiveQuizResults();
        List<QuizResultResponse> responseList = new ArrayList<>();

        for (QuizResult result : results) {
            responseList.add(QuizResultResponse.fromEntity(result));
        }
        return responseList;
    }
    //DELETE (SOFT)
    public void deleteQuizResult(Integer id) throws CustomException {

        QuizResult existingResult = quizResultRepository.findById(id)
                .orElseThrow(() -> new CustomException("QuizResult not found", 404));

        if (Boolean.TRUE.equals(existingResult.getIsActive())) {
            existingResult.setIsActive(false);
            existingResult.setUpdatedAt(new Date());
            quizResultRepository.save(existingResult);
        } else {
            throw new CustomException("QuizResult already inactive", 400);
        }
    }

}
