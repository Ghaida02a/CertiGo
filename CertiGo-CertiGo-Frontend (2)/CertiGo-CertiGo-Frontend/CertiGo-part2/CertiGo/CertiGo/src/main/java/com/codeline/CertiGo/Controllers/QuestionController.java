package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.QuestionCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.QuestionResponse;
import com.codeline.CertiGo.DTOResponse.QuestionWithOptionsResponse;
import com.codeline.CertiGo.DTOUpdateRequest.QuestionUpdateRequest;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("/create")
    public ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody QuestionCreateRequestDTO requestDTO) throws CustomException {
        QuestionResponse question = questionService.saveQuestion(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping("/getAll")
    public List<QuestionResponse> getAllQuestions() {
        List<QuestionResponse> responseList = questionService.getAllQuestions();
        System.out.println(responseList);
        return responseList;
    }

    @GetMapping("/getById")
    public QuestionResponse getQuestionById(@RequestParam Integer id) throws CustomException {
        QuestionResponse questionResponse = questionService.getQuestionById(id);
        return questionResponse;
    }

    @PutMapping("/Update")
    public QuestionResponse updateQuestion(@RequestBody QuestionUpdateRequest updateObjFromUser) throws CustomException {
        QuestionUpdateRequest.validateQuestion(updateObjFromUser);
        return questionService.updateQuestion(updateObjFromUser);
    }

    @DeleteMapping("/delete")
    public String deleteQuestion(@RequestParam int id) throws CustomException {
        questionService.deleteQuestion(id);
        return "Question deleted successfully";
    }

    @GetMapping("/getByQuizId")
    public ResponseEntity<List<QuestionWithOptionsResponse>> getQuestionsByQuizId(@RequestParam Integer quizId) throws CustomException {
        List<QuestionWithOptionsResponse> questions = questionService.getQuestionsWithOptionsByQuizId(quizId);
        return ResponseEntity.ok(questions);
    }
}
