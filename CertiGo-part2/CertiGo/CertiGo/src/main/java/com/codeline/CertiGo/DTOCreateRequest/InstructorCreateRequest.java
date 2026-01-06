package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Instructor;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorCreateRequest {

    private String name;
    private String bio;
    private String email;

    public static Instructor convertToInstructor(InstructorCreateRequest request) {
        Instructor instructor = new Instructor();
        if (Utils.isNotNull(request)) {
            instructor.setName(request.getName());
            instructor.setBio(request.getBio());
            instructor.setEmail(request.getEmail());
        }
        return instructor;
    }

    public static void validCreateInstructorRequest(InstructorCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getName()) || request.getName().isBlank() || request.getName().isEmpty()) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_NAME_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(request.getBio()) || request.getBio().isBlank() || request.getBio().isEmpty()) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_BIO_NOT_VALID ,Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(request.getEmail()) || request.getEmail().isBlank() || !request.getEmail().contains("@")) {
            throw new CustomException(Constants.INSTRUCTOR_CREATE_REQUEST_EMAIL_NOT_VALID ,Constants.HTTP_STATUS_IS_NULL);
        }
    }
}
