package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.LessonCreateRequest;
import com.codeline.CertiGo.DTOCreateResponse.LessonCreateResponse;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Lesson;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repositories.CourseRepository;
import com.codeline.CertiGo.Repositories.LessonRepository;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LessonServices {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public LessonCreateResponse saveLesson(LessonCreateRequest request) throws CustomException {
        Lesson lesson = LessonCreateRequest.ConverToLesson(request);
        lesson.setCreatedAt(new Date());
        lesson.setIsActive(Boolean.TRUE);

        Course course = courseRepository.getCourseById(request.getCourseId())
        if (Utils.isNotNull(course)) {
            Lesson.setCourse(course);
        } else {
            throw new Exception(Constants.LESSON_CREATE_REQUEST_COURSE_ID_NOT_VALID)
        }
        return LessonCreateResponse.convertToLessonCreateResponse(lessonRepository.save(lesson));
    }
        public Lesson updateLesson (Lesson lesson) throws CustomException {
            Lesson existingLesson = lessonRepository.findById(Lesson.getId()).get();
            if (existingLesson != null && existingLesson.getIsActive()) {
                Lesson.setUpdatedAt(new Date());
                return lessonRepository.save(Lesson);
            } else {
                throw new Exception("BAD REQUEST");
            }
        }

        public void deleteLesson (Integer id) throws CustomException {
            Lesson existingLesson = lessonRepository.findById(id).get();
            if (existingLesson != null && existingLesson.getIsActive()) {
                existingLesson.setUpdatedAt(new Date());
                existingLesson.setIsActive(Boolean.FALSE);
                lessonRepository.save(existingLesson);
            } else {
                throw new Exception("BAD REQUEST");
            }
        }

        public Lesson getLessonById (Integer id) throws CustomException {
            Lesson existingLesson = lessonRepository.findById(id).get();
            if (existingLesson != null && existingLesson.getIsActive()) {
                return existingLesson;
            } else {
                throw new Exception("BAD REQUEST");
            }
        }
    }


