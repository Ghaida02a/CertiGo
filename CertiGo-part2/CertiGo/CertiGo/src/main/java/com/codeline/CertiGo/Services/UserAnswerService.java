package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.UserAnswerCreateRequest;
import com.codeline.CertiGo.DTOResponse.UserAnswerCreateResponse;
import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizResultRepository quizResultRepository;

    public List<UserAnswer> getAllUserAnswers() throws CustomException {
        List<UserAnswer> answers = userAnswerRepository.findUserAnswers();
        if (Utils.isListNotEmpty(answers)) {
            return answers;
        } else {
            throw new CustomException(Constants.USER_ANSWER_NOT_VALID, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    public UserAnswerCreateResponse saveAnswer(UserAnswerCreateRequest answerDTO) throws CustomException {
        UserAnswerCreateRequest.validCreateUSerAnswerRequest(answerDTO);
        UserAnswer answer = UserAnswerCreateRequest.convertToUserAnswer(answerDTO);
        answer.setIsCorrect(answerDTO.getIsCorrect());
        answer.setSelectedOption(answerDTO.getSelectedOption());

        if (Utils.isNotNull(answerDTO)) {
            User user = userRepository.getUserById(answerDTO.getUserId());
            if (Utils.isNotNull(user)) {
                answer.setUser(user);
            } else {
                throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_NOT_FOUND);
            }

            Quiz quiz = quizRepository.getQuizById(answerDTO.getQuizId());
            if (Utils.isNotNull(quiz)) {
                answer.setQuiz(quiz);
            } else {
                throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_NOT_FOUND);
            }

            Question question = questionRepository.getQuestionById(answerDTO.getQuestionId());
            if (Utils.isNotNull(question)) {
                answer.setQuestion(question);
            } else {
                throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_NOT_FOUND);
            }
            QuizResult quizResult = quizResultRepository.getQuizResultById(answerDTO.getQuizResultId());
            if (Utils.isNotNull(quizResult)) {
                answer.setQuizResult(quizResult);
            } else {
                throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_NOT_FOUND);
            }
        }

        answer.setCreatedAt(new Date());
        answer.setIsActive(Boolean.TRUE);

        return UserAnswerCreateResponse.convertToUserAnswerResponse(userAnswerRepository.save(answer));
    }

    public UserAnswer updateUserAnswer(UserAnswer answer) throws CustomException {
        UserAnswer existingAnswer = userAnswerRepository.getUserAnswerById(answer.getId());
        if (Utils.isNotNull(existingAnswer) && existingAnswer.getIsActive()) {
            answer.setUpdatedAt(new Date());
            return userAnswerRepository.save(answer);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteUserAnswer(Integer id) throws CustomException {
        UserAnswer existingAnswer = userAnswerRepository.getUserAnswerById(id);
        if (Utils.isNotNull(existingAnswer) && existingAnswer.getIsActive()) {
            existingAnswer.setUpdatedAt(new Date());
            existingAnswer.setIsActive(Boolean.FALSE);
            userAnswerRepository.save(existingAnswer);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public UserAnswer getUserAnswerByUserId(Integer id) throws CustomException{
        UserAnswer existingAnswer = userAnswerRepository.getUserAnswerById(id);
        if (Utils.isNotNull(existingAnswer) && existingAnswer.getIsActive()){
            return existingAnswer;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public UserAnswer getUserAnswerByQuizId(Integer id) throws CustomException{
        UserAnswer existingAnswer = userAnswerRepository.getUserAnswerById(id);
        if (Utils.isNotNull(existingAnswer) && existingAnswer.getIsActive()){
            return existingAnswer;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

}
