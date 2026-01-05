package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.InstructorCreateRequest;
import com.codeline.CertiGo.DTOResponse.InstructorCreateResponse;
import com.codeline.CertiGo.Entity.Instructor;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public InstructorCreateResponse createInstructor(@RequestBody InstructorCreateRequest requestObj) throws CustomException {
        InstructorCreateRequest.validCreateInstructorRequest(requestObj);
        InstructorCreateResponse instructor = instructorService.saveInstructor(requestObj);
        return instructor;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @GetMapping("/getAll")
    public List<Instructor> getAllInstructors() throws CustomException {
        List<Instructor> instructors= instructorService.getAllInstructors();
        return instructors;
    }

    @GetMapping("/getById/{id}")
    public Instructor getInstructorByCourseId(@PathVariable int id) throws CustomException{
        return instructorService.getInstructorByCourseId(id);
    }

    @PutMapping("/update")
    public Instructor updateInstructor(@RequestBody Instructor updateObjFromUser) throws CustomException{
        return instructorService.updateInstructor(updateObjFromUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id) throws CustomException{
        instructorService.deleteInstructor(id);
        return Constants.SUCCESS;
    }

}
