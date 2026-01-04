package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.Entity.Course;
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
public class EnrollmentUpdateRequest {
    private Integer id;
        private EnrollmentStatus status;
         private User username;
        private Integer courseId;

        //   convert DTO → Entity
        public static Enrollment convertToEnrollment(EnrollmentUpdateRequest request) {
            Enrollment enrollment = new Enrollment();
            enrollment.setId(request.getId());
            enrollment.setStatus(request.getStatus());
            enrollment.setUser (request.getUsername());
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            enrollment.setCourse(course);
            return enrollment;
        }

        //  validation
        public static void validateEnrollment(EnrollmentUpdateRequest request) throws CustomException {

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







