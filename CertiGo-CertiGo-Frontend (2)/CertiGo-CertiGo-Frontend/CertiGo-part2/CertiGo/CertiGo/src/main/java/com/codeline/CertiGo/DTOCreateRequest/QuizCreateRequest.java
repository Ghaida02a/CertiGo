package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Entity.Quiz;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.CompositeUserType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizCreateRequest {
    private Integer totalQuestions;
    private Integer passingScore;
    private Integer courseId;

    public static Quiz convertToQuiz(QuizCreateRequest request) {
        Quiz quiz = new Quiz();
        quiz.setTotalQuestions(request.getTotalQuestions());
        quiz.setPassingScore(request.getPassingScore());
        return quiz;

    }
        public static void validQuizCreateRequest(QuizCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getTotalQuestions()) || request.getTotalQuestions() <= 0) {
            throw new CustomException(Constants.QUIZ_CREATE_REQUEST_TOTAL_QUESTIONS_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getPassingScore()) || request.getPassingScore() <= 0) {
            throw new CustomException(Constants.QUIZ_CREATE_REQUEST_PASSING_SCORE_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getCourseId())|| request.getCourseId() <= 0) {
            throw new CustomException(Constants.QUIZ_CREATE_REQUEST_COURSE_ID_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        }
        }
}
