package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionUpdateRequest {
    private Integer id;
    private String optionText;
    private Boolean isCorrect;
    private Question question;


    public static void validateOptionUpdateRequested(OptionUpdateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getId())) {
            throw new CustomException(Constants.OPTION_CREATE_REQUEST_OPTION_TEXT_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getOptionText())) {
            throw new CustomException(Constants.OPTION_CREATE_REQUEST_OPTION_TEXT_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getIsCorrect())) {
            throw new CustomException(Constants.OPTION_CREATE_REQUEST_IS_CORRECT_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getQuestion())) {
            throw new CustomException(Constants.OPTION_CREATE_REQUEST_QUESTION_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }

    public static Option convertDTOToEntity(OptionUpdateRequest dto) {
        Option option = Option.builder()
                .id(dto.getId())
                .optionText(dto.getOptionText())
                .isCorrect(dto.getIsCorrect())
                .question(dto.getQuestion())
                .build();
        return option;
    }

    public static OptionUpdateRequest convertEntityToDTO(Option entity) {
        return OptionUpdateRequest.builder()
                .id(entity.getId())
                .optionText(entity.getOptionText())
                .isCorrect(entity.getIsCorrect())
                .question(entity.getQuestion())
                .build();
    }
}

