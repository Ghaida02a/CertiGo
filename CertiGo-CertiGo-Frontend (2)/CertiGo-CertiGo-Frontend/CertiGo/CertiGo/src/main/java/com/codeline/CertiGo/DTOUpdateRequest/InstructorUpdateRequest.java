package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Instructor;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import jakarta.persistence.ManyToMany;
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
public class InstructorUpdateRequest {
    private Integer id;

    private String name;
    private String bio;
    private String email;
    private List<Course> courses;

    public static void validateInstructorUpdateRequested(InstructorUpdateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getId())) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_INSTRUCTOR_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getName())) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getBio())) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_BIO_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getEmail())) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_EMAIL_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getCourses())) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_COURSES_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }

    public static Instructor convertDTOToEntity(InstructorUpdateRequest dto) {
        Instructor instructor = Instructor.builder()
                .id(dto.getId())
                .name(dto.getName())
                .bio(dto.getBio())
                .email(dto.getEmail())
                .courses(dto.getCourses())
                .build();
        return instructor;
    }

    public static InstructorUpdateRequest convertEntityToDTO(Instructor entity) {
        return InstructorUpdateRequest.builder()
                .id(entity.getId())
                .name(entity.getName())
                .bio(entity.getBio())
                .email(entity.getEmail())
                .courses(entity.getCourses())
                .build();
    }
}
