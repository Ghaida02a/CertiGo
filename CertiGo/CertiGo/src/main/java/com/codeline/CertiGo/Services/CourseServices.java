package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.Entity.Company;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Instructor;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repositories.CompanyRepository;
import com.codeline.CertiGo.Repositories.CourseRepository;
import com.codeline.CertiGo.Repositories.InstructorRepository;
import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.DTOCreateResponse.CourseCreateResponse;
import org.apache.tomcat.util.bcel.classfile.Constant;
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

    public List<Course> getAllCourses() {
        return courseRepository.findAll();

    }

    public CourseCreateResponse saveCourse(CourseCreateRequest request) throws CustomException {
        Course course = CourseCreateRequest.convertToCourse(request);
        course.setCreatedAt(new Date());
        course.setIsActive(Boolean.TRUE);

        Company company = companyRepository.getCompanyById(request.getCompanyId());
        if (Utils.isNotNull(company)) {
            Course.setCompany(company);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }

        List<Instructor> instructor = instructorRepository.getInstructorById(request.getInstructorsId());
        if (Utils.isNotNull(instructor) && Utils.isListNotEmpty(instructor)) {
            course.setInstructors(instructor);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }

        return CourseCreateResponse.convertToCourseCreateResponse(courseRepository.save(course));

    }
    public Course updateCourse(Course course) throws CustomException {
        Course existingCourse = courseRepository.findById(course.getId()).get();
        if (existingCourse != null && existingCourse.getIsActive()) {
            course.setUpdatedAt(new Date());
            return courseRepository.save(course);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteCourse(Integer id) throws CustomException {
        Course existingCourse = courseRepository.findById(id).get();
        if (existingCourse != null && existingCourse.getIsActive()) {
            existingCourse.setUpdatedAt(new Date());
            existingCourse.setIsActive(Boolean.FALSE);
            courseRepository.save(existingCourse);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Course getCourseById(Integer id) throws CustomException {
        Course existingCourse = courseRepository.findById(id).get();
        if (existingCourse != null && existingCourse.getIsActive()) {
            return existingCourse;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
