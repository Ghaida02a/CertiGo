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
    private List<OptionCreateRequest> options;  // Added to match JSON payload

    // convert DTO → Entity (updated to include options, though not directly used in service)
    public static Question convertToQuestion(QuestionCreateRequestDTO request) {
        Question question = new Question();
        question.setQuestionText(request.getQuestionText());
        question.setCorrectAnswer(request.getCorrectAnswer());
        // Note: Quiz and options are handled separately in the service
        return question;
    }

    // validation (updated to include options, but skip questionId check since it's not in JSON)
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
        // Validate options
        if (Utils.isNull(request.getOptions()) || request.getOptions().isEmpty()) {
            throw new CustomException("Options are required and cannot be empty", 400);
        }
        boolean hasCorrectOption = false;
        for (OptionCreateRequest option : request.getOptions()) {
            // Validate optionText and isCorrect only (skip questionId, as it's set in service)
            if (Utils.isNull(option.getOptionText()) || option.getOptionText().isBlank()) {
                throw new CustomException(Constants.OPTION_CREATE_REQUEST_OPTION_TEXT_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
            }
            if (Utils.isNull(option.getIsCorrect())) {
                throw new CustomException(Constants.OPTION_CREATE_REQUEST_IS_CORRECT_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
            }
            if (Boolean.TRUE.equals(option.getIsCorrect()) && option.getOptionText().equals(request.getCorrectAnswer())) {
                hasCorrectOption = true;
            }
        }
        if (!hasCorrectOption) {
            throw new CustomException("At least one option must match the correctAnswer and be marked as isCorrect", 400);
        }
    }
}