package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
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
    private Integer courseId;

    public static QuizCreateResponse ConvertToQuizCreateResponse(Quiz entity) {
        return QuizCreateResponse.builder()
                .id(entity.getId())
                .totalQuestions(entity.getTotalQuestions())
                .passingScore(entity.getPassingScore())
                .courseId(entity.getCourse().getId())
                .build();
    }
}
