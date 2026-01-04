package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Certificate;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.QuizResult;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateResponse {
    private String certificateName;
    private String description;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private Course course;
    private User user;
    private QuizResult quizResult;

    // Convert Entity → ResponseDTO
    public static CertificateResponse entityToDTOResponse(Certificate entity) {
        CertificateResponse dto = new CertificateResponse();
        dto.setCertificateName(entity.getCertificateName());
        dto.setDescription(entity.getDescription());
        dto.setCourse(entity.getCourse());
        dto.setUser(entity.getUser());
        dto.setQuizResult(entity.getQuizResult());
        dto.setIsActive(entity.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    //DTO Response -> Entity
    public static Certificate convertEntityToDTO(CertificateResponse dto) throws CustomException {
        if (Utils.isNull(dto)) {
            throw new CustomException(Constants.CERTIFICATE_NOT_FOUND, Constants.HTTP_STATUS_IS_NULL);
        }
        return Certificate.builder()
                .certificateName(dto.getCertificateName())
                .description(dto.getDescription())
                .course(dto.getCourse())
                .user(dto.getUser())
                .quizResult(dto.getQuizResult())
                .isActive(dto.getIsActive())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
