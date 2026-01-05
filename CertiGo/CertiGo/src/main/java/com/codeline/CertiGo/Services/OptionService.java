package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.OptionCreateRequest;
import com.codeline.CertiGo.DTOResponse.OptionCreateResponse;
import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OptionService {
    @Autowired
    OptionRepository optionRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public OptionCreateResponse saveOption(OptionCreateRequest optionDTO) throws CustomException {
        Option option = OptionCreateRequest.convertToOption(optionDTO);

        option.setIsCorrect(optionDTO.getIsCorrect());
        option.setOptionText(optionDTO.getOptionText());

        Question question = questionRepository.findById(optionDTO.getQuestion()).get();
        if(Utils.isNotNull(question)){
            option.setQuestion(question);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
        }
        option.setCreatedAt(new Date());
        option.setIsActive(Boolean.TRUE);
        return OptionCreateResponse.convertToOptionResponse(optionRepository.save(option));

    }

    public Option updateOption(Option option) throws CustomException {
        Option existingOption = optionRepository.findById(option.getId()).get();
        if (existingOption != null && existingOption.getIsActive()) {
            option.setUpdatedAt(new Date());
            return optionRepository.save(option);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteOption(Integer id) throws CustomException {
        Option existingOption = optionRepository.findById(id).get();
        if (existingOption != null && existingOption.getIsActive()) {
            existingOption.setUpdatedAt(new Date());
            existingOption.setIsActive(Boolean.FALSE);
            optionRepository.save(existingOption);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Option getOptionByQuizId(Integer id) throws CustomException{
        Option existingOption = optionRepository.findById(id).get();
        if (existingOption != null && existingOption.getIsActive()){
            return existingOption;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
