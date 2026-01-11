package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.UserAnswerBulkCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.UserAnswerCreateRequest;
import com.codeline.CertiGo.DTOResponse.EnrollmentResponse;
import com.codeline.CertiGo.DTOResponse.UserAnswerCreateResponse;
import com.codeline.CertiGo.Entity.UserAnswer;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Services.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("UserAnswer")
public class UserAnswerController {
    @Autowired
    UserAnswerService userAnswerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public UserAnswerCreateResponse createAnswer(@RequestBody UserAnswerCreateRequest requestObj) throws CustomException {
        UserAnswerCreateRequest.validCreateUSerAnswerRequest(requestObj);
        UserAnswerCreateResponse answer = userAnswerService.saveAnswer(requestObj);
        return answer;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @GetMapping("/getAll")
    public List<UserAnswer> getAllAnswers() throws CustomException {
        return userAnswerService.getAllUserAnswers();
    }


    @GetMapping("/getByUserId/{id}")
    public UserAnswer getUserAnswerByUserId(@PathVariable int id) throws CustomException{
        return userAnswerService.getUserAnswerByUserId(id);
    }

    @GetMapping("/getByQuizId/{id}")
    public UserAnswer getUserAnswerByQuizId(@PathVariable int id) throws CustomException{
        return userAnswerService.getUserAnswerByQuizId(id);
    }

    @PutMapping("/update")
    public UserAnswer updateUserAnswer(@RequestBody UserAnswer updateObjFromUser) throws CustomException{
        return userAnswerService.updateUserAnswer(updateObjFromUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserAnswer(@PathVariable int id) throws CustomException{
        userAnswerService.deleteUserAnswer(id);
        return Constants.SUCCESS;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createBulk")
    public List<UserAnswerCreateResponse> createAnswersBulk(@RequestBody UserAnswerBulkCreateRequest requestObj) throws CustomException {
        return userAnswerService.saveAnswersBulk(requestObj);
    }
}
