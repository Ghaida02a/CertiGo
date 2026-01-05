package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.InstructorCreateRequest;
import com.codeline.CertiGo.DTOResponse.InstructorCreateResponse;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Instructor;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.CourseRepository;
import com.codeline.CertiGo.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InstructorService {

    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;


    public List<Instructor> getAllInstructors() throws CustomException {
        List<Instructor> instructors = instructorRepository.findAllInstructors();
        if (Utils.isListNotEmpty(instructors)) {
            return instructors;
        } else {
            throw new CustomException(Constants.INSTRUCTOR_LIST_IS_EMPTY, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    public InstructorCreateResponse saveInstructor(InstructorCreateRequest instructorDTO) throws CustomException {
        InstructorCreateRequest.validCreateInstructorRequest(instructorDTO);
        Instructor instructor = InstructorCreateRequest.convertToInstructor(instructorDTO);

        if (Utils.isNotNull(instructorDTO.getCourses()) && !instructorDTO.getCourses().isEmpty()) {
            List<Course> courses = new ArrayList<>();
            for (CourseCreateRequest courseDTO : instructorDTO.getCourses()) {
                if (Utils.isNull(courseDTO.getId()) || courseDTO.getId() <= 0) {
                    throw new CustomException(Constants.COURSE_ID_NOT_VALID, Constants.HTTP_STATUS_NOT_FOUND);
                }

                Course course = courseRepository.getCourseById(courseDTO.getId());
                if (Utils.isNull(course)) {
                     throw new CustomException(Constants.COURSE_ID_NOT_VALID, Constants.HTTP_STATUS_NOT_FOUND);
                }

                courses.add(course);
            }
            instructor.setCourses(courses);
        }
        instructor.setCreatedAt(new Date());
        instructor.setIsActive(Boolean.TRUE);

        return InstructorCreateResponse.convertToInstructorResponse(instructorRepository.save(instructor));
    }

    public Instructor updateInstructor(Instructor inst) throws CustomException {
        Instructor existingInstructor = instructorRepository.getInstructorById(inst.getId());
        if (Utils.isNotNull(existingInstructor) && existingInstructor.getIsActive()) {
            inst.setUpdatedAt(new Date());
            return instructorRepository.save(inst);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteInstructor(Integer id) throws CustomException {
        Instructor existingInstructor = instructorRepository.getInstructorById(id);
        if (Utils.isNotNull(existingInstructor)  && existingInstructor.getIsActive()) {
            existingInstructor.setUpdatedAt(new Date());
            existingInstructor.setIsActive(Boolean.FALSE);
            instructorRepository.save(existingInstructor);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Instructor getInstructorById(Integer id) throws CustomException {
        Instructor existingInstructor = instructorRepository.getInstructorById(id);
        if (Utils.isNotNull(existingInstructor)  && existingInstructor.getIsActive()) {
            return existingInstructor;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Instructor getInstructorByCourseId(Integer id) throws CustomException {
        Instructor existingInstructor = instructorRepository.getInstructorByCourseId(id);
        if (Utils.isNotNull(existingInstructor)  && existingInstructor.getIsActive()) {
            return existingInstructor;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
