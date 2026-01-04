package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.EnrollmentCreateRequestDTO;
import com.codeline.CertiGo.DTOCreateRequest.UserCreateRequest;
import com.codeline.CertiGo.DTOResponse.UserResponse;
import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.EnrollmentRepository;
import com.codeline.CertiGo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    // Get all active users
    public List<User> getAllCourses() {
        if (Utils.isListNotEmpty(userRepository.findAllActiveUsers())) {
            return userRepository.findAllActiveUsers();
        } else {
            return new ArrayList<>();
        }
    }
//
//    public UserResponse saveUser(UserCreateRequest userCreateRequest) {
//        User user = UserCreateRequest.convertDTOToEntity(userCreateRequest);
//        user.setCreatedAt(new Date());
//        user.setIsActive(Boolean.TRUE);
//
////        Instructor instructor = instructorRepository.getInstructorById(courseRequested.getInstructorId());
////        if (Utils.isNotNull(instructor)) {
////            course.setInstructor(instructor);
////        } else {
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.COURSE_CREATE_REQUEST_INSTRUCTOR_ID_NOT_VALID);
////        }
//
//        return UserResponse.entityToDTOResponse(userRepository.save(user));
//    }

    //Get user by ID
    public User getUserById(int id) throws CustomException {
        User userOpt = userRepository.getUserById(id);
        if (Utils.isNotNull(userOpt)) {
            return userOpt;
        } else {
            throw new CustomException(Constants.USER_NOT_FOUND, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    // Update user safely
    public String updateUser(User updateObj) throws CustomException {
        Optional<User> existingOpt = userRepository.findById(updateObj.getId());

        if (existingOpt.isPresent()) {
            User existingCourse = existingOpt.get();

            if (Boolean.TRUE.equals(existingCourse.getIsActive())) {
                existingCourse.setUsername(updateObj.getUsername());
                existingCourse.setEmail(updateObj.getEmail());
                existingCourse.setPassword(updateObj.getPassword());
                existingCourse.setRole(updateObj.getRole());
                existingCourse.setUpdatedAt(new Date());
                existingCourse.setIsActive(Boolean.TRUE);

                userRepository.save(existingCourse);
                return Constants.SUCCESS;
            } else {
                throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
            }
        } else {
            throw new CustomException(Constants.USER_NOT_FOUND, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    //Soft delete user
    public void deleteUser(Integer id) throws CustomException {
        User existingOpt = userRepository.getUserById(id);

        if (Utils.isNotNull(existingOpt)) {
            existingOpt.setUpdatedAt(new Date());
            existingOpt.setIsActive(Boolean.FALSE);
            userRepository.save(existingOpt);
        } else {
            throw new CustomException(Constants.USER_NOT_FOUND, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    public UserResponse saveUser(UserCreateRequest userRequested) {
        User user = UserCreateRequest.convertDTOToEntity(userRequested);
        user.setCreatedAt(new Date());
        user.setIsActive(Boolean.TRUE);

        //    private List<Enrollment> enrollments;
        //    private List<Payment> payments;
        //    private List<QuizResult> quizResults;
        //    private List<UserAnswer> userAnswers;
        //    private List<Certificate> certificates;

        if (Utils.isNotNull(userRequested.getEnrollments()) && !userRequested.getEnrollments().isEmpty()) {
            List<Enrollment> enrollments = new ArrayList<>();
            for (EnrollmentCreateRequestDTO enrollmentDTO : userRequested.getEnrollments()) {
                Enrollment mark = MarkRequestDTO.convertDTOToEntity(enrollmentDTO);
                mark.setCourse(course);
                mark.setIsActive(Boolean.TRUE);
                mark.setCreatedDate(new Date());
                // Save mark to mark table
                Mark savedMark = markRepository.save(mark);
                enrollments.add(savedMark);
            }

            course.setMarks(enrollments);
        }

        User savedUser = userRepository.save(user);

        return UserResponse.entityToDTOResponse(savedUser);
    }
}
