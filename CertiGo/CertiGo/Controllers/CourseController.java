package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.DTOResponse.CourseCreateResponse;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseServices courseServices;

    @PostMapping("create")

    public CourseCreateResponse createCourse(@RequestBody CourseCreateRequest requestObj) throws CustomException {
        CourseCreateRequest.validCreateCourseRequest(requestObj);
        return courseServices.saveCourse(requestObj);

    }

    @GetMapping("getAll")
    public List<Course> getAllCourses() {
        List<Course> courseList = courseServices.getAllCourses();
        return courseList;
    }

    @GetMapping("getById")
    public Course getCourseById(@RequestParam int id)throws CustomException{
        return courseServices.getCourseById(id);
    }

    @PutMapping("update")
    public Course updateCourse(@RequestBody Course updateObjectFromUser) throws CustomException {
        return courseServices.updateCourse(updateObjectFromUser);

    }

    @DeleteMapping("delete/{id}")
    public String deleteCourse(@PathVariable int id) throws CustomException {
        courseServices.deleteCourse(id);
        return "Success";

    }
}

