package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.EnrollmentCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.EnrollmentResponse;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeline.CertiGo.Repository.EnrollmentRepository;
import com.codeline.CertiGo.Repository.CourseRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    // SAVE
    public EnrollmentResponse saveEnrollment(EnrollmentCreateRequestDTO request)
            throws CustomException {

        Enrollment enrollment = EnrollmentCreateRequestDTO.convertToEnrollment(request);

        enrollment.setIsActive(true);
        enrollment.setCreatedAt(new Date());

        User user = userRepository.getUserByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(Constants.USERNAME_NOT_VALID, 404));
        enrollment.setUser(user);


        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CustomException("Course not found", 404));
        enrollment.setCourse(course);

        Enrollment saved = enrollmentRepository.save(enrollment);
        return EnrollmentResponse.fromEntity(saved);
    }



    //  GET ALL
    public List<EnrollmentResponse> getAllEnrollments() {

        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<EnrollmentResponse> responseList = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (Boolean.TRUE.equals(enrollment.getIsActive())) {
                responseList.add(EnrollmentResponse.fromEntity(enrollment));
            }
        }
        return responseList;
    }

    // GET BY ID
    public EnrollmentResponse getEnrollmentById(Integer id) throws CustomException {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new CustomException(Constants.BAD_REQUEST, 404));

        if (Boolean.TRUE.equals(enrollment.getIsActive())) {
            return EnrollmentResponse.fromEntity(enrollment);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, 400);
        }
    }

    // DELETE (SOFT)
    public void deleteEnrollment(Integer id) throws CustomException {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new CustomException(Constants.BAD_REQUEST, 404));

        if (Boolean.TRUE.equals(enrollment.getIsActive())) {
            enrollment.setIsActive(false);
            enrollment.setUpdatedAt(new Date());
            enrollmentRepository.save(enrollment);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, 400);
        }
    }
}
