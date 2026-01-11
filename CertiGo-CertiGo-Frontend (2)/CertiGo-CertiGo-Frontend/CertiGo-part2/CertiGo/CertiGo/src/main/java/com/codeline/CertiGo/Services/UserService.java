package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.UserCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.PaymentCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.CertificateCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.QuizResultCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.UserResponse;
import com.codeline.CertiGo.DTOUpdateRequest.UserUpdateRequest;
import com.codeline.CertiGo.Entity.Payment;
import com.codeline.CertiGo.Entity.Certificate;
import com.codeline.CertiGo.Entity.QuizResult;
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

    // Get user by ID
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
        existingUser.setUsername(updateObj.getUsername());
        existingUser.setEmail(updateObj.getEmail());
        existingUser.setPassword(updateObj.getPassword());
        existingUser.setRole(updateObj.getRole());
        existingUser.setUpdatedAt(new Date());

        User savedUser = userRepository.save(existingUser);
        return UserResponse.entityToDTOResponse(savedUser);
    }

    //Delete user
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

    // Save user with relationships
    public UserResponse saveUser(UserCreateRequest userRequested) {
        User user = UserCreateRequest.convertDTOToEntity(userRequested);
        user.setCreatedAt(new Date());
        user.setIsActive(Boolean.TRUE);

        User savedUser = userRepository.save(user);
        return UserResponse.entityToDTOResponse(savedUser);
    }
}
