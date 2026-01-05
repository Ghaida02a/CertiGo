package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.UserAnswerCreateRequest;
import com.codeline.CertiGo.DTOResponse.UserAnswerCreateResponse;
import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;

    public List<UserAnswer> getAllUSerAnswers() {
        return userAnswerRepository.findAll();
    }

    public UserAnswerCreateResponse saveAnswer(UserAnswerCreateRequest answerDTO) throws CustomException {
        UserAnswer answer = UserAnswerCreateRequest.convertToUserAnswer(answerDTO);
        answer.setIsCorrect(answerDTO.getIsCorrect());
        answer.setSelectedOption(answerDTO.getSelectedOption());
        User user = userRepository.findById(answerDTO.getUserId()).get();
        if(Utils.isNotNull(user)){
            answer.setUser(user);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
        }
        Quiz quiz = quizRepository.findById(answerDTO.getQuizId()).get();
        if(Utils.isNotNull(quiz)){
            answer.setQuiz(quiz);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
        }
        Question question = questionRepository.findById(answerDTO.getQuestionId()).get();
        if(Utils.isNotNull(question)){
            answer.setQuestion(question);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
        }
        QuizResult quizResult = quizResultRepository.findById(answerDTO.getQuizResultId()).get();
        if(Utils.isNotNull(quizResult)){
            answer.setQuizResult(quizResult);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
        }

        answer.setCreatedAt(new Date());
        answer.setIsActive(Boolean.TRUE);
        return UserAnswerCreateResponse.convertToUserAnswerResponse(userAnswerRepository.save(answer));

    }

    public UserAnswer updateUserAnswer(UserAnswer answer) throws CustomException {
        UserAnswer existingAnswer = userAnswerRepository.findById(answer.getId()).get();
        if (existingAnswer != null && existingAnswer.getIsActive()) {
            answer.setUpdatedAt(new Date());
            return userAnswerRepository.save(answer);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteUserAnswer(Integer id) throws CustomException {
        UserAnswer existingAnswer = userAnswerRepository.findById(id).get();
        if (existingAnswer != null && existingAnswer.getIsActive()) {
            existingAnswer.setUpdatedAt(new Date());
            existingAnswer.setIsActive(Boolean.FALSE);
            userAnswerRepository.save(existingAnswer);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public UserAnswer getUserAnswerByUserId(Integer id) throws CustomException{
        UserAnswer existingAnswer = userAnswerRepository.findById(id).get();
        if (existingAnswer != null && existingAnswer.getIsActive()){
            return existingAnswer;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public UserAnswer getUserAnswerByQuizId(Integer id) throws CustomException{
        UserAnswer existingAnswer = userAnswerRepository.findById(id).get();
        if (existingAnswer != null && existingAnswer.getIsActive()){
            return existingAnswer;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
