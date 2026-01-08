package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.QuizResult;
import com.codeline.CertiGo.Entity.UserAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizResultResponse {
    private Integer id;
    private Integer score;
    private Boolean isPassed;
    private Integer userId;
    private Integer quizId;
    private Integer courseId;
    // Convert Entity → DTO
    public  static QuizResultResponse fromEntity(QuizResult quizResult) {
        return QuizResultResponse.builder()
                .id(quizResult.getId())
                .score(quizResult.getScore())
                .isPassed(quizResult.getIsPassed())
                .userId(quizResult.getUser().getId())
                .quizId(quizResult.getQuiz().getId())
                .courseId(quizResult.getCourse().getId())
                .build();
    }
}
