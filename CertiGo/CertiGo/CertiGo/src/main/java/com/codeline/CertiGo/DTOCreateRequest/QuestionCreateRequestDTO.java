package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Entity.UserAnswer;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionCreateRequestDTO {
    private String questionText;
    private String correctAnswer;
    private Integer quiz_id;
    private List<Option> options;
    private List<UserAnswer> userAnswers;

    // convert DTO → Entity
    public static Question convertToQuestion(QuestionCreateRequestDTO request) {
        Question question = new Question();
        question.setQuestionText(request.getQuestionText());
        question.setCorrectAnswer(request.getCorrectAnswer());
        //question.setQuiz(request.getQuiz());
       // question.setOptions(request.getOptions());
       // question.setUserAnswers(request.getUserAnswers());
        return question;
    }

    // validation
    public static void validateQuestion(QuestionCreateRequestDTO request) throws CustomException {

        if (Utils.isNull(request) || Utils.isNull(request.getQuiz_id())) {
            throw new CustomException(Constants.QUIZ_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isBlank(request.getQuestionText()) || Utils.isNull(request.getQuestionText())) {
            throw new CustomException(Constants.QUESTION_TEXT_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isBlank(request.getCorrectAnswer()) || Utils.isNull(request.getCorrectAnswer())) {
            throw new CustomException(Constants.CORRECT_ANSWER_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isListEmpty(request.getOptions())) {
            throw new CustomException(Constants.OPTION_NOT_FOUND, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isListEmpty(request.getUserAnswers())) {
            throw new CustomException(Constants.USER_ANSWER_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }

}
