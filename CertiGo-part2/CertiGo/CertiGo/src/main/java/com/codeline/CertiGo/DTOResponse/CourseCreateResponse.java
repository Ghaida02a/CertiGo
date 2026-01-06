package com.codeline.CertiGo.DTOCreateResponse;

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
    private List<Integer> instructorId;

    public static CourseCreateResponse convertToCourseCreateResponse(Course entity){
        return CourseCreateResponse.builder()
                .id(entity.getId())
                .courseName(entity.getCourseName())
                .type(entity.getType())
                .durationHours(entity.getDurationHours())
                .price(entity.getPrice())
                .isCompleted(entity.getIsCompleted())
                .companyId(entity.getCompany().getId())
                .instructorId(entity.getInstructors().stream().map(instructor->instructor.getId()).collect(Collectors.toList()))
                .build();
    }
}
