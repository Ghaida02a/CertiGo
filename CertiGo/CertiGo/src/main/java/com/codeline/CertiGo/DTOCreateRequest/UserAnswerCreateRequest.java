package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerCreateRequest {
    private String selectedOption;
    private Boolean isCorrect;
    private Integer userId;
    private Integer quizId;
    private Integer questionId;
    private Integer quizResultId;


    public static UserAnswer convertToUserAnswer(UserAnswerCreateRequest request) {
        UserAnswer answer = new UserAnswer();
        if (request != null) {
           answer.setSelectedOption(request.getSelectedOption());
           answer.setIsCorrect(request.getIsCorrect());
        }
        return answer;
    }

    public static void validCreateUSerAnswerRequest(UserAnswerCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getSelectedOption()) || request.getSelectedOption().isBlank() || request.getSelectedOption().isEmpty()) {
            throw new CustomException(Constants.USER_ANSWER_CREATE_REQUEST_SELECTED_OPTION_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getIsCorrect())) {
            throw new CustomException(Constants.USER_ANSWER_CREATE_REQUEST_IS_CORRECT_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getUserId()) || request.getUserId() <= 0) {
            throw new CustomException(Constants.USER_ANSWER_USER_ID_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getQuizId()) || request.getQuizId() <= 0) {
            throw new CustomException(Constants.USER_ANSWER_QUIZ_ID_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getQuestionId()) || request.getQuestionId() <= 0) {
            throw new CustomException(Constants.USER_ANSWER_QUESTION_ID_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getQuizResultId()) || request.getQuizResultId() <= 0) {
            throw new CustomException(Constants.USER_ANSWER_QUIZ_RESULT_ID_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }
    }
}
