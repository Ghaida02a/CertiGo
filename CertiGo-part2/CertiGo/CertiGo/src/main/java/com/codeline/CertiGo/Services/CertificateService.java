package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.CertificateCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.QuizResultCreateRequestDTO;
import com.codeline.CertiGo.DTOCreateRequest.UserCreateRequest;
import com.codeline.CertiGo.DTOResponse.CertificateResponse;
import com.codeline.CertiGo.Entity.Certificate;
import com.codeline.CertiGo.Entity.QuizResult;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.CertificationRepository;
import com.codeline.CertiGo.Repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CertificateService {

    @Autowired
    CertificationRepository certificationRepository;
    @Autowired
    QuizResultService quizResultService;
    @Autowired
    QuizResultRepository quizResultRepository;

    // Get all active certificates
    public List<Certificate> getAllCertificates() throws CustomException {
        List<Certificate> certificates = certificationRepository.findAllActiveCertifications();
        if (Utils.isListNotEmpty(certificates)) {
            return certificates;
        } else {
            throw new CustomException(Constants.CERTIFICATE_LIST_IS_EMPTY, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    // Get certificate by ID
    public Certificate getCertificateById(Integer id) throws CustomException {
        Certificate certificate = certificationRepository.getCertificateById(id);
        if (Utils.isNotNull(certificate)) {
            return certificate;
        } else {
            throw new CustomException(Constants.CERTIFICATE_NOT_FOUND, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    public CertificateResponse saveCertificate(CertificateCreateRequest certificateCreateRequest) throws CustomException {
        CertificateCreateRequest.validateCertificateCreateRequested(certificateCreateRequest);
        Certificate certificate = CertificateCreateRequest.convertDTOToEntity(certificateCreateRequest);
        certificate.setCreatedAt(new Date());
        certificate.setIsActive(Boolean.TRUE);

        if (Utils.isNotNull(certificateCreateRequest.getUser())) {
            certificate.setUser(UserCreateRequest.convertDTOToEntity(certificateCreateRequest.getUser()));
        }

        if (Utils.isNotNull(certificateCreateRequest.getCourse())) {
            certificate.setCourse(CourseCreateRequest.convertToCourse(certificateCreateRequest.getCourse()));
        }

        if (Utils.isNotNull(certificateCreateRequest.getQuizResult())) {
            QuizResult quizResult =
                    QuizResultCreateRequestDTO.convertToQuizResult(
                            certificateCreateRequest.getQuizResult()
                    );

            quizResult = quizResultRepository.save(quizResult);
            certificate.setQuizResult(quizResult);
        }

        Certificate savedCertificate = certificationRepository.save(certificate);
        return CertificateResponse.entityToDTOResponse(savedCertificate);
    }


    //Delete certificate
    public void deleteCertificate(Integer id) throws CustomException {
        Certificate existingCertificate = certificationRepository.getCertificateById(id);

        if (Utils.isNotNull(existingCertificate)) {
            existingCertificate.setUpdatedAt(new Date());
            existingCertificate.setIsActive(Boolean.FALSE);
            certificationRepository.save(existingCertificate);
        } else {
            throw new CustomException(Constants.CERTIFICATE_NOT_FOUND, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }
}
