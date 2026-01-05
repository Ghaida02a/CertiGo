package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.*;
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
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateRequest {
    private String courseName;
    private String type;
    private Integer durationHours;
    private Double price;
    private Boolean isCompleted;
    private Integer companyId;
    private List<Integer> instructorsId;
    private List<Integer> lessonsId;
    private List<Integer> enrollmentsId;
    private List<Integer> paymentsId;
    private List<Integer> quizzesId;
    private List<Integer> certificatesId;


    public static Course convertToCourse(CourseCreateRequest request) {
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setType(request.getType());
        course.setDurationHours(request.getDurationHours());
        course.setPrice(request.getPrice());
        course.setIsCompleted(request.getIsCompleted());
        return course;
    }

    public static void validCreateCourseRequest(CourseCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getCourseName()) || request.getCourseName().isEmpty() || request.getCourseName().isBlank()) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_COURSE_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getType()) || request.getType().isBlank() || request.getType().isEmpty()) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_TYPE_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getDurationHours()) || request.getDurationHours() <= Constants.LOWER_DURATION_RANGE ||
                request.getDurationHours() >= Constants.UPPER_DURATION_RANGE) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_DURATION_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getPrice()) || request.getPrice() <= 0) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_PRICE_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getIsCompleted())) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_IS_COMPLETED_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
//        }else if (Utils.isNull(request.getCompanyId()) || request.getCompanyId() <= 0) {
//            throw new CustomException(Constants.COURSE_CREATE_REQUEST_COMPANY_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
//        }else if (Utils.isNull(request.getInstructorsId())|| Utils.isListEmpty(request.getInstructorsId())) {
//            throw new CustomException(Constants.COURSE_CREATE_REQUEST_INSTRUCTORS_ID_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
//        }
//
        }
    }
}