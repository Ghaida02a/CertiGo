package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionCreateRequest {

    private String optionText;
    private Boolean isCorrect;
    private Integer questionId;

    public static Option convertToOption(OptionCreateRequest request) {
        Option option = new Option();
        if (request != null) {
            option.setOptionText(request.getOptionText());
            option.setIsCorrect(request.getIsCorrect());
        }
        return option;
    }

    public static void validCreateOptionRequest(OptionCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getOptionText()) || request.getOptionText().isBlank()) {
            throw new CustomException(Constants.OPTION_CREATE_REQUEST_OPTION_TEXT_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getIsCorrect())) {
            throw new CustomException(Constants.OPTION_CREATE_REQUEST_IS_CORRECT_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getQuestionId()) || request.getQuestionId() <= 0) {
            throw new CustomException(Constants.OPTION_CREATE_REQUEST_QUESTION_ID_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }
    }
}
