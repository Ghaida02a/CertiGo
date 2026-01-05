package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.QuizResultCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.QuizResultResponse;
import com.codeline.CertiGo.DTOUpdateRequest.QuestionUpdateRequest;
import com.codeline.CertiGo.DTOUpdateRequest.QuizResultUpdateRequest;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.QuizResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/quizResult")
public class QuizResultController {
    @Autowired
    QuizResultService quizResultService;

    @PostMapping("/create")
    public ResponseEntity<QuizResultResponse> createQuizResult(@Valid @RequestBody QuizResultCreateRequestDTO request) throws CustomException {
        QuizResultResponse createdQuizResult = quizResultService.saveQuizResult(request);
        return ResponseEntity.status(201).body(createdQuizResult);
    }

    @GetMapping("/getAll")
    public List<QuizResultResponse> getAllQuizResults() {
        List<QuizResultResponse> responseList = quizResultService.getAllQuizResults();
        System.out.println(responseList);
        return responseList;
    }

    @GetMapping("/getById")
    public QuizResultResponse getQuizResultById(@RequestParam Integer id) throws CustomException {
        QuizResultResponse quizResult = quizResultService.getQuizResultById(id);
        return quizResult;
    }

    @DeleteMapping("/delete")
    public String deleteQuizResult(@RequestParam int id) throws CustomException {
        quizResultService.deleteQuizResult(id);
        return "Quiz Result deleted successfully";
    }

}
