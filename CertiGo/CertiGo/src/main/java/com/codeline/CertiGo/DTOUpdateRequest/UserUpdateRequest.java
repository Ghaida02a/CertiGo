package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.DTOCreateRequest.UserCreateRequest;
import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Enum.UserRole;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private List<Enrollment> enrollments;
    private List<Payment> payments;
    private List<QuizResult> quizResults;
    private List<UserAnswer> userAnswers;
    private List<Certificate> certificates;

    //Validation
    public static void validateUserUpdateRequested(UserUpdateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getId())) {
            throw new CustomException(Constants.USER_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getUserAnswers())) {
            throw new CustomException(Constants.USER_USER_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getEmail())) {
            throw new CustomException(Constants.USER_EMAIL_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getPassword())) {
            throw new CustomException(Constants.USER_PASSWORD_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getRole())) {
            throw new CustomException(Constants.USER_ROLE_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getEnrollments()) || dto.getEnrollments().isEmpty()) {
            throw new CustomException(Constants.USER_ENROLLMENTS_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getPayments()) || dto.getPayments().isEmpty()) {
            throw new CustomException(Constants.USER_PAYMENTS_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getQuizResults()) || dto.getQuizResults().isEmpty()) {
            throw new CustomException(Constants.USER_QUIZ_RESULTS_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getUserAnswers()) || dto.getUserAnswers().isEmpty()) {
            throw new CustomException(Constants.USER_USER_ANSWERS_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getCertificates()) || dto.getCertificates().isEmpty()) {
            throw new CustomException(Constants.USER_CERTIFICATES_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }

    // Convert DTO → Entity
    public static User convertDTOToEntity(UserUpdateRequest dto) {
        User user = User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .enrollments(dto.getEnrollments())
                .payments(dto.getPayments())
                .quizResults(dto.getQuizResults())
                .userAnswers(dto.getUserAnswers())
                .certificates(dto.getCertificates())
                .build();
        return user;
    }

    // Convert Entity → DTO
    public static UserUpdateRequest convertEntityToDTO(User entity) {
        return UserUpdateRequest.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .enrollments(entity.getEnrollments())
                .payments(entity.getPayments())
                .quizResults(entity.getQuizResults())
                .userAnswers(entity.getUserAnswers())
                .certificates(entity.getCertificates())
                .build();
    }
}
