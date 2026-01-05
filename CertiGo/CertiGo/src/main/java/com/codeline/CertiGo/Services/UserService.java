package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.UserCreateRequest;
import com.codeline.CertiGo.DTOResponse.UserResponse;
import com.codeline.CertiGo.DTOUpdateRequest.UserUpdateRequest;
import com.codeline.CertiGo.Entity.Payment;
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
    public List<User> getAllActiveUsers() {
        if (Utils.isListNotEmpty(userRepository.findAllActiveUsers())) {
            return userRepository.findAllActiveUsers();
        } else {
            return new ArrayList<>();
        }
    }

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
    public UserResponse updateUser(UserUpdateRequest updateObj) throws CustomException {
        Optional<User> existingOpt = userRepository.findById(updateObj.getId());

        if (existingOpt.isEmpty()) {
            throw new CustomException(Constants.USER_NOT_FOUND, Constants.HTTP_STATUS_NOT_FOUND);
        }

        User existingUser = existingOpt.get();

        if (!Boolean.TRUE.equals(existingUser.getIsActive())) {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }

        // Apply updates
        existingUser.setUsername(updateObj.getUsername());
        existingUser.setEmail(updateObj.getEmail());
        existingUser.setPassword(updateObj.getPassword());
        existingUser.setRole(updateObj.getRole());
        existingUser.setUpdatedAt(new Date());

        // Handle relationships if provided
        if (Utils.isNotNull(updateObj.getEnrollments())) existingUser.setEnrollments(updateObj.getEnrollments());
        if (Utils.isNotNull(updateObj.getPayments())) existingUser.setPayments(updateObj.getPayments());
        if (Utils.isNotNull(updateObj.getQuizResults())) existingUser.setQuizResults(updateObj.getQuizResults());
        if (Utils.isNotNull(updateObj.getUserAnswers())) existingUser.setUserAnswers(updateObj.getUserAnswers());
        if (Utils.isNotNull(updateObj.getCertificates())) existingUser.setCertificates(updateObj.getCertificates());

        User savedUser = userRepository.save(existingUser);
        return UserResponse.entityToDTOResponse(savedUser);
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
        // Convert DTO to entity
        User user = UserCreateRequest.convertDTOToEntity(userRequested);
        user.setCreatedAt(new Date());
        user.setIsActive(Boolean.TRUE);

//        // Handle enrollments
//        if (Utils.isNotNull(userRequested.getEnrollments()) && !userRequested.getEnrollments().isEmpty()) {
//            List<Enrollment> enrollments = new ArrayList<>();
//            for (EnrollmentCreateRequestDTO enrollmentDTO : userRequested.getEnrollments()) {
//                Enrollment enrollment = EnrollmentCreateRequestDTO.convertToEnrollment(enrollmentDTO);
//                enrollment.setUser(user);
//                enrollment.setStatus(enrollmentDTO.getStatus());
//                enrollment.setIsActive(Boolean.TRUE);
//                enrollment.setCreatedAt(new Date());
//
//                // If course is part of enrollment, set it here
//                if (Utils.isNotNull(enrollmentDTO.getCourse())) {
//                    enrollment.setCourse(enrollmentDTO.getCourse());
//                }
//
//                enrollments.add(enrollment);
//            }
//            user.setEnrollments(enrollments);
//        }

        // Handle payments
        if (Utils.isNotNull(userRequested.getPayments()) && !userRequested.getPayments().isEmpty()) {
            List<Payment> payments = new ArrayList<>();
            for (Payment payment : userRequested.getPayments()) {
                payment.setUser(user);
                payment.setIsActive(Boolean.TRUE);
                payment.setCreatedAt(new Date());
                payments.add(payment);
            }
            user.setPayments(payments);
        }

        // Handle quiz results
        if (Utils.isNotNull(userRequested.getQuizResults()) && !userRequested.getQuizResults().isEmpty()) {
            userRequested.getQuizResults().forEach(qr -> {
                qr.setUser(user);
                qr.setIsActive(Boolean.TRUE);
                qr.setCreatedAt(new Date());
            });
            user.setQuizResults(userRequested.getQuizResults());
        }

//        // Handle user answers
//        if (Utils.isNotNull(userRequested.getUserAnswers()) && !userRequested.getUserAnswers().isEmpty()) {
//            userRequested.getUserAnswers().forEach(ans -> {
//                ans.setUser(user);
//                ans.setIsActive(Boolean.TRUE);
//                ans.setCreatedAt(new Date());
//            });
//            user.setUserAnswers(userRequested.getUserAnswers());
//        }

        // Handle certificates
        if (Utils.isNotNull(userRequested.getCertificates()) && !userRequested.getCertificates().isEmpty()) {
            userRequested.getCertificates().forEach(cert -> {
                cert.setUser(user);
                cert.setIsActive(Boolean.TRUE);
                cert.setCreatedAt(new Date());
            });
            user.setCertificates(userRequested.getCertificates());
        }

        // Save user
        User savedUser = userRepository.save(user);

        return UserResponse.entityToDTOResponse(savedUser);
    }
}
