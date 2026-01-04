package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Enum.UserRole;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateRequest {
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private List<EnrollmentCreateRequestDTO> enrollments;
    private List<PaymentCreateRequest> payments;
    private List<QuizResultCreateRequestDTO> quizResults;
    private List<UserAnswerCreateRequest> userAnswers;
    private List<CertificateCreateRequest> certificates;

    // Validation
    public static void validateUserCreateRequested(UserCreateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getUsername()) || dto.getUsername().isBlank()) {
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
    }

    // Convert DTO → Entity
    public static User convertDTOToEntity(UserCreateRequest dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword()) // hash in service layer
                .role(dto.getRole())
                .build();

        if (Utils.isNotNull(dto.getEnrollments())) {
            user.setEnrollments(dto.getEnrollments().stream()
                    .map(EnrollmentCreateRequestDTO::convertToEnrollment)
                    .toList());
        }
        if (Utils.isNotNull(dto.getPayments())) {
            user.setPayments(dto.getPayments().stream()
                    .map(PaymentCreateRequest::convertDTOToEntity)
                    .toList());
        }
        if (Utils.isNotNull(dto.getQuizResults())) {
            user.setQuizResults(dto.getQuizResults().stream()
                    .map(QuizResultCreateRequestDTO::convertToQuizResult)
                    .toList());
        }
        if (Utils.isNotNull(dto.getUserAnswers())) {
            user.setUserAnswers(dto.getUserAnswers().stream()
                    .map(UserAnswerCreateRequest::convertToUserAnswer)
                    .toList());
        }
        if (Utils.isNotNull(dto.getCertificates())) {
            user.setCertificates(dto.getCertificates().stream()
                    .map(CertificateCreateRequest::convertDTOToEntity)
                    .toList());
        }

        return user;
    }

    // Convert Entity → DTO
    public static UserCreateRequest convertEntityToDTO(User entity) {
        return UserCreateRequest.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .enrollments(entity.getEnrollments().stream()
                        .map(EnrollmentCreateRequestDTO::convertToDTO)
                        .toList())
                .payments(entity.getPayments().stream()
                        .map(PaymentCreateRequest::convertEntityToDTO)
                        .toList())
                .quizResults(entity.getQuizResults().stream()
                        .map(QuizResultCreateRequestDTO::convertToQuizResult)
                        .toList())
                .userAnswers(entity.getUserAnswers().stream()
                        .map(UserAnswerCreateRequest::convertToUserAnswerCreateRequest)
                        .toList())
                .certificates(entity.getCertificates().stream()
                        .map(CertificateCreateRequest::convertEntityToDTO)
                        .toList())
                .build();
    }
}