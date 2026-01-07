package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Course;
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
    private Integer id;
    private String courseName;
    private String type;
    private Integer durationHours;
    private Double price;
    private Boolean isCompleted;
    private CompanyCreateRequestDTO company;
    private List<InstructorCreateRequest> instructors;

    public static Course convertToCourse(CourseCreateRequest request) {
        Course course = new Course();
        course.setId(request.getId());
        course.setCourseName(request.getCourseName());
        course.setType(request.getType());
        course.setDurationHours(request.getDurationHours());
        course.setPrice(request.getPrice());
        course.setIsCompleted(request.getIsCompleted());
        return course;
    }

    public static void validCreateCourseRequest(CourseCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getCourseName()) || request.getCourseName().isEmpty() || request.getCourseName().isBlank()) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_COURSE_NAME_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getType()) || request.getType().isBlank() || request.getType().isEmpty()) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_TYPE_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getDurationHours()) || request.getDurationHours() <= Constants.LOWER_DURATION_RANGE ||
                request.getDurationHours() >= Constants.UPPER_DURATION_RANGE) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_DURATION_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getPrice()) || request.getPrice() <= 0) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_PRICE_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getIsCompleted())) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_IS_COMPLETED_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getCompany())) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_COMPANY_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }else if (Utils.isNull(request.getInstructors())|| Utils.isListEmpty(request.getInstructors())) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_INSTRUCTORS_ID_NOT_VALID,Constants.HTTP_STATUS_IS_NULL);
        }

    }

    public static CourseCreateRequest convertToCourse(Course course) {
        return CourseCreateRequest.builder()
                .courseName(course.getCourseName())
                .type(course.getType())
                .durationHours(course.getDurationHours())
                .price(course.getPrice())
                .isCompleted(course.getIsCompleted())
                .build();
    }
}
