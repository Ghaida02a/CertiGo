package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Enum.UserRole;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import jdk.jshell.execution.Util;
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
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private List<EnrollmentResponse> enrollments;
    private List<PaymentResponse> payments;
    private List<QuizResultResponse> quizResults;
    private List<UserAnswer> userAnswers;
    private List<CertificateResponse> certificates;

    // Convert Entity → ResponseDTO
    public static UserResponse entityToDTOResponse(User entity) {
        UserResponse dto = new UserResponse();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setIsActive(entity.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    //DTO Response -> Entity
    public static User convertEntityToDTO(UserResponse dto) throws CustomException{
        if (Utils.isNull(dto)) {
            throw new CustomException(Constants.USER_NOT_FOUND, Constants.HTTP_STATUS_IS_NULL);
        }
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .isActive(dto.getIsActive())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
