package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.DTOCreateRequest.QuizCreateRequest;
import com.codeline.CertiGo.Entity.Quiz;
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
public class QuizUpdateRequest {
    private Integer id;
    private Integer totalQuestions;
    private Integer passingScore;
    private Integer courseId;

    public static Quiz convertToQuiz(QuizUpdateRequest request) {
        Quiz quiz = new Quiz();
        quiz.setId(request.getId());
        quiz.setTotalQuestions(request.getTotalQuestions());
        quiz.setPassingScore(request.getPassingScore());
        return quiz;

    }
    public static void validQuizUpdateRequest(QuizUpdateRequest request) throws CustomException {
        if (Utils.isNull(request.getTotalQuestions()) || request.getTotalQuestions() <= 0) {
            throw new CustomException(Constants.QUIZ_CREATE_REQUEST_TOTAL_QUESTIONS_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getPassingScore()) || request.getPassingScore() <= 0) {
            throw new CustomException(Constants.QUIZ_CREATE_REQUEST_PASSING_SCORE_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getCourseId())|| request.getCourseId() <= 0) {
            throw new CustomException(Constants.QUIZ_CREATE_REQUEST_COURSE_ID_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        }
    }
}




