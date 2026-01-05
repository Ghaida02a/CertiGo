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

    public List<Instructor> getAllInstructor() {
        return instructorRepository.findAll();
    }


    public InstructorCreateResponse saveInstructor(InstructorCreateRequest instructorDTO) throws CustomException {
        Instructor instructor = InstructorCreateRequest.convertToInstructor(instructorDTO);
        instructor.setBio(instructorDTO.getBio());
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());

        if(Utils.isNotNull(instructorDTO.getCourses()) && !instructorDTO.getCourses().isEmpty()){
            List<Course> courses = new ArrayList<>();
            for (CourseCreateRequest courseCreateRequest : instructorDTO.getCourses()) {
                Course course = courseRepository.findById(courseCreateRequest.getId()).get();
                if(Utils.isNotNull(course)){
                    courses.add(course);
                } else {
                    throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
                }
            }
            instructor.setCourses(courses);
        }

        instructor.setCreatedAt(new Date());
        instructor.setIsActive(Boolean.TRUE);

        return InstructorCreateResponse.convertToInstructorResponse(instructorRepository.save(instructor));

    }

    public Instructor updateInstructor(Instructor inst) throws CustomException {
        Instructor existingInstructor = instructorRepository.findById(inst.getId()).get();
        if (existingInstructor != null && existingInstructor.getIsActive()) {
            inst.setUpdatedAt(new Date());
            return instructorRepository.save(inst);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteInstructor(Integer id) throws CustomException {
        Instructor existingInstructor = instructorRepository.findById(id).get();
        if (existingInstructor != null && existingInstructor.getIsActive()) {
            existingInstructor.setUpdatedAt(new Date());
            existingInstructor.setIsActive(Boolean.FALSE);
            instructorRepository.save(existingInstructor);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Instructor getInstructorById(Integer id) throws CustomException {
        Instructor existingInstructor = instructorRepository.findById(id).get();
        if (existingInstructor != null && existingInstructor.getIsActive()) {
            return existingInstructor;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Instructor getInstructorByCourseId(Integer id) throws CustomException {
        Instructor existingInstructor = instructorRepository.findById(id).get();
        if (existingInstructor != null && existingInstructor.getIsActive()) {
            return existingInstructor;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
