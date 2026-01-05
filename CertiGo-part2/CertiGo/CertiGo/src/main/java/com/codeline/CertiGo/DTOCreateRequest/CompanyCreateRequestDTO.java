package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Company;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyCreateRequestDTO {
    private String companyName;
    private String location;
    private String industry;
    private String contactEmail;
    private List<CourseCreateRequest> courses;

    // DTO → Entity
    public static Company convertToCompany(CompanyCreateRequestDTO dto) {
        Company company = Company.builder()
                .companyName(dto.getCompanyName())
                .location(dto.getLocation())
                .industry(dto.getIndustry())
                .contactEmail(dto.getContactEmail())
                .courses(dto.getCourses().stream()
                        .map(CourseCreateRequest::convertToCourse) // assuming you have this method
                        .collect(Collectors.toList()))
                .build();
        return company;
    }

    public static void validCreateCompanyRequest(CompanyCreateRequestDTO dto) throws CustomException {
        if (Utils.isNull(dto.getCompanyName())) {
            throw new CustomException(Constants.COMPANY_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getLocation())) {
            throw new CustomException(Constants.LOCATION_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getIndustry())) {
            throw new CustomException(Constants.INDUSTRY_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getContactEmail())) {
            throw new CustomException(Constants.CONTACT_EMAIL_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }
}
