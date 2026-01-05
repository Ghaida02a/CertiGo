package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Instructor;
import com.codeline.CertiGo.Entity.UserAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerCreateResponse {

    private Integer id;
    private String selectedOption;
    private Boolean isCorrect;

    public static UserAnswerCreateResponse convertToUserAnswerResponse(UserAnswer entity) {
        UserAnswerCreateResponse response = UserAnswerCreateResponse.builder()
                .id(entity.getId())
                .selectedOption(entity.getSelectedOption())
                .isCorrect(entity.getIsCorrect())
                .build();
        return response;
    }
}
