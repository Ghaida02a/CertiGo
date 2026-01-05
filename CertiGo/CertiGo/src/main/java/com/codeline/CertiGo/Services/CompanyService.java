package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.CompanyCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.CompanyResponse;
import com.codeline.CertiGo.DTOUpdateRequest.CompanyUpdateRequest;
import com.codeline.CertiGo.Entity.Company;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeline.CertiGo.Repository.CompanyRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    // SAVE
    public CompanyResponse saveCompany(CompanyCreateRequestDTO request) throws CustomException {

        // Create company entity
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocation(request.getLocation());
        company.setIndustry(request.getIndustry());
        company.setContactEmail(request.getContactEmail());
        company.setIsActive(true);
        company.setCreatedAt(new Date());

        // Attach courses (full Course objects from DTO)
        List<Course> courses = new ArrayList<>();
        if (request.getCourses() != null && !request.getCourses().isEmpty()) {
            for (Course course : request.getCourses()) {
                course.setCompany(company); // set the relationship
                courses.add(course);
            }
        }
        company.setCourses(courses);

        // Save company
        Company savedCompany = companyRepository.save(company);

        // Convert to response DTO
        return CompanyResponse.fromEntity(savedCompany);
    }

    public CompanyResponse updateCompany(CompanyUpdateRequest request) throws CustomException {

        // Get the ID from the DTO
        Integer id = request.getCompanyId();

        // Find existing company
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CustomException(Constants.COURSE_CREATE_REQUEST_COMPANY_ID_NOT_VALID, 404));

        // Check if active
        if (!Boolean.TRUE.equals(existingCompany.getIsActive())) {
            throw new CustomException(Constants.BAD_REQUEST, 400);
        }

        // Update basic fields
        existingCompany.setCompanyName(request.getCompanyName());
        existingCompany.setLocation(request.getLocation());
        existingCompany.setIndustry(request.getIndustry());
        existingCompany.setContactEmail(request.getContactEmail());
        existingCompany.setUpdatedAt(new Date());

        // Update courses (List<Course> from DTO)
        List<Course> courses = new ArrayList<>();
        if (request.getCourses() != null && !request.getCourses().isEmpty()) {
            for (Course course : request.getCourses()) {
                course.setCompany(existingCompany); // maintain relationship
                courses.add(course);
            }
        }
        existingCompany.setCourses(courses);

        // Save updated company
        Company savedCompany = companyRepository.save(existingCompany);

        // Convert to response DTO
        return CompanyResponse.fromEntity(savedCompany);
    }


    // GET ALL
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAllActiveCompanies();
        List<CompanyResponse> responseList = new ArrayList<>();
        for (Company company : companies) {
            if (Boolean.TRUE.equals(company.getIsActive())) {
                responseList.add(CompanyResponse.fromEntity(company));
            }
        }
        return responseList;
    }

    // GET BY ID
    public CompanyResponse getCompanyById(Integer id) throws CustomException {
        Company company = companyRepository.getCompanyById(id);
        if (Utils.isNull(company)) {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }
        if (Boolean.TRUE.equals(company.getIsActive())) {
            return CompanyResponse.fromEntity(company);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    // GET BY NAME
    public CompanyResponse getCompanyByName(String name) throws CustomException {
        Company company = companyRepository.getCompanyByName(name);
        if (Utils.isNull(company)) {
            throw new CustomException(Constants.COURSE_CREATE_REQUEST_COMPANY_ID_NOT_VALID, Constants.HTTP_STATUS_BAD_REQUEST);
        }

        if (Boolean.TRUE.equals(company.getIsActive())) {
            return CompanyResponse.fromEntity(company);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }


    // DELETE (SOFT)
    public void deleteCompany(Integer id) throws CustomException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CustomException(Constants.COURSE_CREATE_REQUEST_COMPANY_ID_NOT_VALID, 404));

        if (Boolean.TRUE.equals(company.getIsActive())) {
            company.setIsActive(false);
            company.setUpdatedAt(new Date());
            companyRepository.save(company);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, 400);
        }

    }
}


