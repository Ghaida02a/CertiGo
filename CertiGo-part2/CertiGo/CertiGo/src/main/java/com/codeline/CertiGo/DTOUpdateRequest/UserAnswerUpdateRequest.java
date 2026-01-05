package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAnswerUpdateRequest {
    private Integer id;
    private String selectedOption;
    private Boolean isCorrect;
    private User user;
    private Quiz quiz;
    private Question question;
    private QuizResult quizResult;

    public static void validateUserAnswerUpdateRequested(UserAnswerUpdateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getId())) {
            throw new CustomException(Constants.USER_ANSWER_USER_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getSelectedOption())) {
            throw new CustomException(Constants.USER_ANSWER_CREATE_REQUEST_SELECTED_OPTION_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getIsCorrect())) {
            throw new CustomException(Constants.USER_ANSWER_CREATE_REQUEST_IS_CORRECT_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getUser())) {
            throw new CustomException(Constants.USER_ANSWER_USER_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getQuiz())) {
            throw new CustomException(Constants.USER_ANSWER_QUIZ_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getQuestion())) {
            throw new CustomException(Constants.USER_ANSWER_QUESTION_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getQuizResult())) {
            throw new CustomException(Constants.USER_ANSWER_QUIZ_RESULT_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }

    public static UserAnswer convertDTOToEntity(UserAnswerUpdateRequest dto) {
        UserAnswer userAnswer = UserAnswer.builder()
                .id(dto.getId())
                .selectedOption(dto.getSelectedOption())
                .isCorrect(dto.getIsCorrect())
                .user(dto.getUser())
                .quiz(dto.getQuiz())
                .question(dto.getQuestion())
                .quizResult(dto.getQuizResult())
                .build();
        return userAnswer;
    }

    public static UserAnswerUpdateRequest convertEntityToDTO(UserAnswer entity) {
        return UserAnswerUpdateRequest.builder()
                .id(entity.getId())
                .selectedOption(entity.getSelectedOption())
                .isCorrect(entity.getIsCorrect())
                .user(entity.getUser())
                .quiz(entity.getQuiz())
                .question(entity.getQuestion())
                .quizResult(entity.getQuizResult())
                .build();
    }
}
