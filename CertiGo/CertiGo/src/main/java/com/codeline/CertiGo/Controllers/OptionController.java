package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.OptionCreateRequest;
import com.codeline.CertiGo.DTOResponse.OptionCreateResponse;
import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Option")
public class OptionController {

    @Autowired
    OptionService optionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public OptionCreateResponse createOption(@RequestBody OptionCreateRequest requestObj) throws CustomException {
        OptionCreateRequest.validCreateOptionRequest(requestObj);
        OptionCreateResponse option = optionService.saveOption(requestObj);
        return option;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @GetMapping("/getAll")
    public List<Option> getAllOptions() throws CustomException {
        List<Option> options= optionService.getAllOptions();
        return options;
    }

    @GetMapping("/getById/{id}")
    public Option getOptionByQuizId(@PathVariable int id) throws CustomException{
        return optionService.getOptionByQuizId(id);
    }

    @PutMapping("/update")
    public Option updateOption(@RequestBody Option updateObjFromUser) throws CustomException{
        return optionService.updateOption(updateObjFromUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOption(@PathVariable int id) throws CustomException{
        optionService.deleteOption(id);
        return Constants.SUCCESS;
    }
}
