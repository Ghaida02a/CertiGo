package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Certificate;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateCreateRequest {
    private String certificateName;
    private String description;
    private CourseCreateRequest course;
    private UserCreateRequest user;
    private QuizResultCreateRequestDTO quizResult;

    //Validation
    public static void validateCertificateCreateRequested(CertificateCreateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getCertificateName())) {
            throw new CustomException(Constants.CERTIFICATE_CREATE_REQUEST_CERTIFICATE_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getDescription())) {
            throw new CustomException(Constants.CERTIFICATE_CREATE_REQUEST_DESCRIPTION_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }

    // Convert DTO → Entity
    public static Certificate convertDTOToEntity(CertificateCreateRequest dto) {
        Certificate certificate = Certificate.builder()
                .certificateName(dto.getCertificateName())
                .description(dto.getDescription())
                .course(CourseCreateRequest.convertToCourse(dto.getCourse()))
                .user(UserCreateRequest.convertDTOToEntity(dto.getUser()))
                .quizResult(QuizResultCreateRequestDTO.convertToQuizResult(dto.getQuizResult()))
                .build();
        return certificate;
    }

    // Convert Entity → DTO
    public static CertificateCreateRequest convertEntityToDTO(Certificate entity) {
        return CertificateCreateRequest.builder()
                .certificateName(entity.getCertificateName())
                .description(entity.getDescription())
                .course(CourseCreateRequest.convertToCourse(entity.getCourse()))
                .user(UserCreateRequest.convertEntityToDTO(entity.getUser()))
                .quizResult(QuizResultCreateRequestDTO.convertToQuizResult(entity.getQuizResult()))
                .build();
    }
}
