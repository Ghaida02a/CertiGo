package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.DTOCreateRequest.*;
import com.codeline.CertiGo.Entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateResponse {
    private Integer id;
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



    public static CourseCreateResponse convertToCourseCreateResponse(Course entity){
        return CourseCreateResponse.builder()
                .id(entity.getId())
                .courseName(entity.getCourseName())
                .type(entity.getType())
                .durationHours(entity.getDurationHours())
                .price(entity.getPrice())
                .isCompleted(entity.getIsCompleted())
                .companyId(entity.getCompany().getId())
                .instructorsId(entity.getInstructors().stream().map(instructor->instructor.getId()).collect(Collectors.toList()))
                .lessonsId(entity.getLessons().stream().map(lesson->lesson.getId()).collect(Collectors.toList()))
                .enrollmentsId(entity.getEnrollments().stream().map(enrollment->enrollment.getId()).collect(Collectors.toList()))
                .paymentsId(entity.getPayments().stream().map(payment->payment.getId()).collect(Collectors.toList()))
                .quizzesId(entity.getQuizzes().stream().map(quiz->quiz.getId()).collect(Collectors.toList()))
                .certificatesId(entity.getCertificates().stream().map(certificate->certificate.getId()).collect(Collectors.toList()))
                .build();
    }
}
