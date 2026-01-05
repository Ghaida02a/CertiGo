package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.Entity.QuizResult;
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
public class QuizResultUpdateRequest {
    private Integer id;
    private Integer score;
    private Boolean isPassed;
    private Integer userId;
    private Integer quizId;
    private Integer courseId;
    private List<UserAnswer> userAnswers;

    //  convert DTO → Entity
    public static QuizResult convertToQuizResult(QuizResultUpdateRequest request) {
        QuizResult quizResult = new QuizResult();
        quizResult.setId(request.getId());
        quizResult.setScore(request.getScore());
        quizResult.setIsPassed(request.getIsPassed());
        quizResult.setUserAnswers(request.getUserAnswers());
       // quizResult.setCourse();
        // quizResult.setUser();
        // quizResult.setQuiz();
        return quizResult;
    }

    // validation
    public static void validateQuizResult(QuizResultUpdateRequest request) throws CustomException {

        if (Utils.isNull(request) || Utils.isBlank(request.getIsPassed())) {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isNull(request.getScore()) || request.getScore() < 0) {
            throw new CustomException(Constants.SCORE_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isNull(request.getUserId()) || request.getUserId() <= 0) {
            throw new CustomException(Constants.USER_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isNull(request.getQuizId()) || request.getQuizId() <= 0) {
            throw new CustomException(Constants.QUIZ_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL
            );
        }

        if (Utils.isNull(request.getCourseId()) || request.getCourseId() <= 0) {
            throw new CustomException(Constants.COURSE_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL
            );
        }
    }

}
