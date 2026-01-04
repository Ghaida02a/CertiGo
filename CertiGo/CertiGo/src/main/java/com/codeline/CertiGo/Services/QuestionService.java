package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOResponse.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeline.CertiGo.Repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
//    public StudentResponseDTO saveStudent(StudentCreateRequestDTO studentDTO) throws Exception {
//        Student student = new Student();
//        student.setFirstName(studentDTO.getFirstName());
//        student.setLastName(studentDTO.getLastName());
//        student.setEmail(studentDTO.getEmail());
//        student.setDateOfBirth(studentDTO.getDateOfBirth());
//        student.setGender(studentDTO.getGender());
//        student.setCreatedDate(new Date());
//        student.setIsActive(true);
//       // student.setAddress(AddressCreateRequestDTO.convertToAddress(studentDTO.getAddressRequestDTO()));
//        student.setPhoneNumbers(PhoneNumberRequestDTO.convertToPhoneNumber(studentDTO.getPhoneNumbers()));
//        return  StudentResponseDTO.convertToDto(studentRepository.save(student));
//    }
    public QuestionResponse saveQuestion(QuestionResponse questionDTO) throws Exception {
        return QuestionResponse.convertToDto(questionRepository.save(QuestionResponse.convertToQuestion(questionDTO)));
    }
}
