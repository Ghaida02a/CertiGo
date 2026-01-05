package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.EnrollmentCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.EnrollmentResponse;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping("Create")
    public ResponseEntity<EnrollmentResponse> createEnrollment(@Valid @RequestBody EnrollmentCreateRequestDTO requestObj) throws CustomException {
        EnrollmentCreateRequestDTO.validateEnrollment(requestObj);
        EnrollmentResponse createdEnrollment = enrollmentService.saveEnrollment(requestObj);
        return ResponseEntity.status(201).body(createdEnrollment);

    }

    @GetMapping("/getAll")
    public List<EnrollmentResponse> getAllEnrollments() {
        List<EnrollmentResponse> enrollmentResponseList = enrollmentService.getAllEnrollments();
        System.out.println(enrollmentResponseList);
        return enrollmentResponseList;
    }

    @GetMapping("/getById")
    public EnrollmentResponse getEnrollmentById(@RequestParam Integer id) throws CustomException {
        EnrollmentResponse enrollmentResponse = enrollmentService.getEnrollmentById(id);
        return enrollmentResponse;
    }

    @DeleteMapping("/delete")
    public String deleteEnrollment(@RequestParam int id) throws CustomException {
        enrollmentService.deleteEnrollment(id);
        return "Enrollment deleted successfully";
    }

}
