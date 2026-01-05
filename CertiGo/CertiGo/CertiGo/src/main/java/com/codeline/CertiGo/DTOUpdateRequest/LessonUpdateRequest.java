package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.DTOCreateRequest.LessonCreateRequest;
import com.codeline.CertiGo.Entity.Lesson;
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
public class LessonUpdateRequest {
    private Integer id;
    private String lessonName;
    private String pdfUrl;
    private Integer courseId;

    public static Lesson ConverToLesson(LessonUpdateRequest request) {
        Lesson lesson = new Lesson();
        lesson.setId(request.getId());
        lesson.setLessonName(request.getLessonName());
        lesson.setPdfUrl(request.getPdfUrl());
        return lesson;

    }

    public static void validLessonUpdateRequest(LessonUpdateRequest request) throws CustomException {
        if (Utils.isNull(request.getLessonName()) || request.getLessonName().isEmpty() || request.getLessonName().isBlank()) {
            throw new CustomException(Constants.LESSON_CREATE_REQUEST_LESSON_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getPdfUrl()) || request.getPdfUrl().isEmpty() || request.getPdfUrl().isBlank()) {
            throw new CustomException(Constants.LESSON_CREATE_REQUEST_PDF_URL_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getCourseId()) || request.getCourseId() <= 0) {
            throw new CustomException(Constants.LESSON_CREATE_REQUEST_COURSE_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }

    }

}

