package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Enrollment;

import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Enum.EnrollmentStatus;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentCreateRequestDTO {
    private EnrollmentStatus status;
    //private String username;
   private User username;
    private Integer courseId;

    //   convert DTO → Entity
    public static Enrollment convertToEnrollment(EnrollmentCreateRequestDTO request) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStatus(request.getStatus());
       enrollment.setUser (request.getUsername());
      // enrollment.setCourseId(request.getCourseId());
        return enrollment;
    }

    //convert Entity → DTO
    public static EnrollmentCreateRequestDTO convertToDTO(Enrollment enrollment) {
        return EnrollmentCreateRequestDTO.builder()
                .status(enrollment.getStatus())
               // .username(enrollment.getUser())
               // .courseId(enrollment.getCourseId())
                .build();
    }

    //  validation
    public static void validateEnrollment(EnrollmentCreateRequestDTO request) throws CustomException {

        if (Utils.isNull(request) || Utils.isBlank(request)) {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(request.getStatus()) || Utils.isBlank(request.getStatus())) {
            throw new CustomException(Constants.ENROLLMENT_STATUS_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }

        if (Utils.isBlank(request.getUsername()) || Utils.isNull(request.getUsername())) {
            throw new CustomException(Constants.USERNAME_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(request.getCourseId()) || Utils.isBlank(request.getCourseId())) {
            throw new CustomException(Constants.COURSE_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);

        }
    }
}







