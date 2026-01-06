package com.codeline.CertiGo.DTOResponse;

<<<<<<< HEAD:CertiGo/CertiGo/src/main/java/com/codeline/CertiGo/DTOResponse/QuizCreateResponse.java
import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.QuestionCreateRequestDTO;
import com.codeline.CertiGo.DTOCreateRequest.QuizResultCreateRequestDTO;
=======
>>>>>>> e434ea2a8d5f5f37a737e89fd1e2585e65a657b6:CertiGo/CertiGo/DTOResponse/QuizCreateResponse.java
import com.codeline.CertiGo.Entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizCreateResponse {
    private Integer id;
    private Integer totalQuestions;
    private Integer passingScore;
    private Integer coursesId;
    private List<Integer> questionsId;
    private List<Integer> quizResultsId;

    public static QuizCreateResponse ConvertToQuizCreateResponse(Quiz entity) {
        return QuizCreateResponse.builder()
                .id(entity.getId())
                .totalQuestions(entity.getTotalQuestions())
                .passingScore(entity.getPassingScore())
                .coursesId(entity.getCourse().getId())
                .questionsId(entity.getQuestions().stream().map(question -> question.getId()).collect(Collectors.toList()))
                .quizResultsId(entity.getQuizResults().stream().map(quizResult -> quizResult.getId()).collect(Collectors.toList()))
                .build();
    }
}
