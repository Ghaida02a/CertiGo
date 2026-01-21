package com.codeline.CertiGo.DTOResponse;
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
    private Integer courseId;

    public static LessonCreateResponse convertToLessonCreateResponse(Lesson entity) {
        return LessonCreateResponse.builder()
                .id(entity.getId())
                .lessonName(entity.getLessonName())
                .pdfUrl(entity.getPdfUrl())
                .courseId(entity.getCourse().getId())
                .build();
    }
}
