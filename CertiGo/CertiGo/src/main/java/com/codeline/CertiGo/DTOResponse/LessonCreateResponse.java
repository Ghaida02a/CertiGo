package com.codeline.CertiGo.DTOCreateResponse;

import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.Entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonCreateResponse {
    private Integer id;
    private String lessonName;
    private String pdfUrl;
    private Integer coursesId;

    public static LessonCreateResponse convertToLessonCreateResponse(Lesson entity) {
        return LessonCreateResponse.builder()
                .id(entity.getId())
                .lessonName(entity.getLessonName())
                .pdfUrl(entity.getPdfUrl())
                .coursesId(entity.getCourse().getId())
                .build();
    }
}
