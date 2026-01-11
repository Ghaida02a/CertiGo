package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Entity.Question;
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
public class QuestionResponse {
    private Integer id;
    private String questionText;
    private String correctAnswer;
    private Integer quiz_id;
    private List<Option> options;


    // Convert Entity → DTO
    public static QuestionResponse fromEntity(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .correctAnswer(question.getCorrectAnswer())
                .quiz_id(question.getQuiz().getId())
                .options(question.getOptions())
                .build();
    }
}
