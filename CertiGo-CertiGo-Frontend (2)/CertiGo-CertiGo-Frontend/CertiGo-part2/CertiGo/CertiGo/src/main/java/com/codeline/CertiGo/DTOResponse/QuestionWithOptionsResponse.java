package com.codeline.CertiGo.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithOptionsResponse {
    private Integer id;
    private String questionText;
    private List<OptionResponse> options;
}
