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
    private List<Enrollment> enrollments;
    private List<Payment> payments;
    private List<QuizResult> quizResults;
    private List<UserAnswer> userAnswers;
    private List<Certificate> certificates;

    //Validation
    public static void validateUserCreateRequested(UserCreateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getUserAnswers())) {
            throw new CustomException(Constants.USER_USER_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getEmail())) {
            throw new CustomException(Constants.USER_EMAIL_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getPassword())) {
            throw new CustomException(Constants.USER_PASSWORD_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if(Utils.isNull(dto.getRole())) {
            throw new CustomException(Constants.USER_ROLE_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }


    // Convert DTO → Entity
    public static User convertDTOToEntity(UserCreateRequest dto) {
        User user = User.builder()
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
    public static UserCreateRequest convertEntityToDTO(User entity) {
        return UserCreateRequest.builder()
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
