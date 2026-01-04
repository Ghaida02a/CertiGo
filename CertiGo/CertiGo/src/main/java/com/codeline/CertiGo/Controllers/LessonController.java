package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.LessonCreateRequest;
import com.codeline.CertiGo.DTOCreateResponse.LessonCreateResponse;
import com.codeline.CertiGo.Entity.Lesson;
import com.codeline.CertiGo.Entity.Quiz;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.LessonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Lesson")
public class LessonController {
    @Autowired
    LessonServices lessonServices;

    @PostMapping("create")
    public LessonCreateResponse createLesson(@RequestBody LessonCreateRequest requestObj) throws CustomException {
        LessonCreateRequest.validLessonCreateRequest(requestObj);
        return lessonServices.saveLesson(requestObj);
    }

    @GetMapping("getAll")
    public List<Lesson> getAllLesson() throws CustomException {
        List<Lesson> lessonList = lessonServices.getAllLesson();
        return lessonList;
    }

    @GetMapping("getById")
    public Lesson getLessonById(@RequestParam("id") int id) throws CustomException {
        return lessonServices.getLessonById(id);
    }

    @PutMapping("update")
    public Lesson updateLesson(@RequestBody Lesson updateObjectFromUser) throws CustomException {
        return lessonServices.updateQuiz(updateObjectFromUser);

    }

    @DeleteMapping("delete/{id}")
    public String deleteLesson(@PathVariable int id) throws CustomException {
        LessonServices.deleteLesson(id);
        return "Success";

    }

}
