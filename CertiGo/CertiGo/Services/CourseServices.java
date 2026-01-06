package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOResponse.CourseCreateResponse;
import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
<<<<<<< HEAD:CertiGo/CertiGo/src/main/java/com/codeline/CertiGo/Services/CourseServices.java
import com.codeline.CertiGo.Repository.*;
import com.codeline.CertiGo.Repositories.CourseRepository;
import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.Repository.InstructorRepository;
import com.codeline.CertiGo.Repository.QuestionRepository;
import org.apache.tomcat.util.bcel.classfile.Constant;
=======
import com.codeline.CertiGo.Repository.CompanyRepository;
import com.codeline.CertiGo.Repository.CourseRepository;
import com.codeline.CertiGo.Repository.InstructorRepository;
import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.DTOCreateResponse.CourseCreateResponse;
>>>>>>> e434ea2a8d5f5f37a737e89fd1e2585e65a657b6:CertiGo/CertiGo/Services/CourseServices.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class CourseServices {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CertificationRepository certificateRepository;
    @Autowired
    QuizRepository quizRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();

    }

    public CourseCreateResponse saveCourse(CourseCreateRequest request) throws CustomException {
        Course course = CourseCreateRequest.convertToCourse(request);
        course.setCreatedAt(new Date());
        course.setIsActive(Boolean.TRUE);

        Company company = companyRepository.getCompanyById(request.getCompanyId());
        if (Utils.isNotNull(company)) {
            course.setCompany(company);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }

        List<Instructor> instructor = instructorRepository.getInstructorById(request.getInstructorsId());
        if (Utils.isNotNull(instructor) && Utils.isListNotEmpty(instructor)) {
            course.setInstructors(instructor);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }

        List<Lesson> lessons = lessonRepository.getLessonById(request.getLessonsId());
        if (Utils.isNotNull(lessons) && Utils.isListNotEmpty(lessons)) {
            course.setLessons(lessons);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }

        List<Enrollment> enrollments =enrollmentRepository.getEnrollmentById(request.getEnrollmentsId());
        if (Utils.isNotNull(enrollments) && Utils.isListNotEmpty(enrollments)) {
            course.setEnrollments(enrollments);
        }else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }

        List<Payment> payments=paymentRepository.getPaymentById(request.getPaymentsId());
        if (Utils.isNotNull(payments) && Utils.isListNotEmpty(payments)) {
            course.setPayments(payments);
        }else{
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }

        List<Quiz> quizzes= quizRepository.getQuizById(request.getQuizzesId());
        if (Utils.isNotNull(quizzes) && Utils.isListNotEmpty(quizzes)) {
            course.setQuizzes(quizzes);
        }else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }

        List<Certificate> certificates=certificateRepository.getCertificateById(request.getCertificatesId());
        if (Utils.isNotNull(certificates) && Utils.isListNotEmpty(certificates)) {
            course.setCertificates(certificates);
        }else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }

        return CourseCreateResponse.convertToCourseCreateResponse(courseRepository.save(course));

    }
    public Course updateCourse(Course course) throws CustomException {
        Course existingCourse = courseRepository.findById(course.getId()).get();
        if (Utils.isNotNull(existingCourse) && existingCourse.getIsActive()) {
            course.setUpdatedAt(new Date());
            return courseRepository.save(course);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteCourse(Integer id) throws CustomException {
        Course existingCourse = courseRepository.findById(id).get();
        if (Utils.isNotNull(existingCourse) && existingCourse.getIsActive()) {
            existingCourse.setUpdatedAt(new Date());
            existingCourse.setIsActive(Boolean.FALSE);
            courseRepository.save(existingCourse);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Course getCourseById(Integer id) throws CustomException {
        Course existingCourse = courseRepository.findById(id).get();
        if (Utils.isNotNull(existingCourse)&& existingCourse.getIsActive()) {
            return existingCourse;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
