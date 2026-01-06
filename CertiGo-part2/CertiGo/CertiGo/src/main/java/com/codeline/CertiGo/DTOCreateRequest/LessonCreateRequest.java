package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Lesson;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonCreateRequest {
    private String lessonName;
    private String pdfUrl;
    private Integer courseId;

    public static Lesson ConverToLesson(LessonCreateRequest request) {
        Lesson lesson = new Lesson();
        lesson.setLessonName(request.getLessonName());
        lesson.setPdfUrl(request.getPdfUrl());
        return lesson;

    }

    public static void validLessonCreateRequest(LessonCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getLessonName()) || request.getLessonName().isEmpty() || request.getLessonName().isBlank()) {
            throw new CustomException(Constants.LESSON_CREATE_REQUEST_LESSON_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getPdfUrl()) || request.getPdfUrl().isEmpty() || request.getPdfUrl().isBlank()) {
            throw new CustomException(Constants.LESSON_CREATE_REQUEST_PDF_URL_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getCourseId()) || request.getCourseId() <= 0) {
            throw new CustomException(Constants.LESSON_CREATE_REQUEST_COURSE_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }
}



