package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.Entity.Quiz;
import com.codeline.CertiGo.DTOCreateRequest.QuizCreateRequest;
import com.codeline.CertiGo.DTOCreateResponse.QuizCreateResponse;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")

    public QuizCreateResponse createQuiz(@RequestBody QuizCreateRequest requestObj) throws CustomException {
        QuizCreateRequest.validCreateQuizRequestObject(requestObj);
        return QuizService.saveQuiz(requestObj);

    }

    @GetMapping("getAll")
    public List<Quiz> getAllQuizzes() throws CustomException {
        List<Quiz> quizList= quizService.getAllQuizzes();
        return quizList;
    }

    @GetMapping("getById")
    public Quiz getQuizById(@RequestParam int id) throws CustomException {
        return quizService.getQuizById(id);
    }

    @PutMapping("update")
    public Quiz updateQuiz(@RequestBody Quiz updateObjectFromUser) throws CustomException {
        return quizService.updateQuiz(updateObjectFromUser);

    }

    @DeleteMapping("delete/{id}")
    public String deleteQuiz(@PathVariable int id) throws CustomException {
        quizService.deleteQuiz(id);
        return "Success";

    }
}


