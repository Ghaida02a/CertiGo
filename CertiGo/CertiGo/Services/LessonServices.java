package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.LessonCreateRequest;
import com.codeline.CertiGo.DTOCreateResponse.LessonCreateResponse;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Lesson;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
<<<<<<< HEAD:CertiGo/CertiGo/src/main/java/com/codeline/CertiGo/Services/LessonServices.java
import com.codeline.CertiGo.Repositories.CourseRepository;
import com.codeline.CertiGo.Repository.LessonRepository;
import com.codeline.CertiGo.Repository.LessonRepository;
import jdk.jshell.execution.Util;
=======
import com.codeline.CertiGo.Repository.CourseRepository;
import com.codeline.CertiGo.Repositories.LessonRepository;
>>>>>>> e434ea2a8d5f5f37a737e89fd1e2585e65a657b6:CertiGo/CertiGo/Services/LessonServices.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LessonServices {
    @Autowired
<<<<<<< HEAD:CertiGo/CertiGo/src/main/java/com/codeline/CertiGo/Services/LessonServices.java
=======
    LessonRepository lessonRepository;

    @Autowired
>>>>>>> e434ea2a8d5f5f37a737e89fd1e2585e65a657b6:CertiGo/CertiGo/Services/LessonServices.java
    CourseRepository courseRepository;
    @Autowired
    LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public LessonCreateResponse saveLesson(LessonCreateRequest request) throws CustomException {
        Lesson lesson = LessonCreateRequest.ConverToLesson(request);
        lesson.setCreatedAt(new Date());
        lesson.setIsActive(Boolean.TRUE);

        Course course = courseRepository.getCourseById(request.getCoursesId());
        if (Utils.isNotNull(course)) {
            lesson.setCourse(course);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
        return LessonCreateResponse.convertToLessonCreateResponse(lessonRepository.save(lesson));
    }

        public Lesson updateLesson (Lesson lesson) throws CustomException {
            Lesson existingLesson = lessonRepository.findById(lesson.getId()).get();
            if (existingLesson != null && existingLesson.getIsActive()) {
                lesson.setUpdatedAt(new Date());
                return lessonRepository.save(lesson);
            } else {
                throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
            }
        }

        public void deleteLesson (Integer id) throws CustomException {
            Lesson existingLesson = lessonRepository.findById(id).get();
            if (existingLesson != null && existingLesson.getIsActive()) {
                existingLesson.setUpdatedAt(new Date());
                existingLesson.setIsActive(Boolean.FALSE);
                lessonRepository.save(existingLesson);
            } else {
                throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
            }
        }

        public Lesson getLessonById (Integer id) throws CustomException {
            Lesson existingLesson = lessonRepository.findById(id).get();
            if (existingLesson != null && existingLesson.getIsActive()) {
                return existingLesson;
            } else {
                throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
            }
        }
    }


