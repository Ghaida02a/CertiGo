package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionCreateResponse {
    private Integer id;
    private String optionText;
    private Boolean isCorrect;
    private QuestionResponse question;

    public static OptionCreateResponse convertToOptionResponse(Option entity) {
        OptionCreateResponse response = OptionCreateResponse.builder()
                .id(entity.getId())
                .optionText(entity.getOptionText())
                .isCorrect(entity.getIsCorrect())
                .question(QuestionResponse.fromEntity(entity.getQuestion()))
                .build();
        return response;
    }
}
