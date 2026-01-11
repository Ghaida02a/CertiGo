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
public class OptionResponse {
    private Integer id;
    private String optionText;
    private Boolean isCorrect;

    public static OptionResponse fromEntity(Option option) {
        return OptionResponse.builder()
                .id(option.getId())
                .optionText(option.getOptionText())
                .isCorrect(option.getIsCorrect())
                .build();
    }
}
