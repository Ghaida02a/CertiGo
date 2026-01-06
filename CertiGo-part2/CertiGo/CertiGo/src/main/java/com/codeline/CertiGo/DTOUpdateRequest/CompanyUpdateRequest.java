package com.codeline.CertiGo.DTOUpdateRequest;

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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyUpdateRequest {
    private Integer companyId;
    private String companyName;
    private String location;
    private String industry;
    private String contactEmail;

    //DTO → Entity
    public static Company covertToCompany(CompanyUpdateRequest dto) {
        Company company = new Company();
        company.setId(dto.getCompanyId());
        company.setCompanyName(dto.getCompanyName());
        company.setLocation(dto.getLocation());
        company.setIndustry(dto.getIndustry());
        company.setContactEmail(dto.getContactEmail());
        return company;
    }

    public static void validCreateCompanyRequest(CompanyUpdateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getCompanyName()) || Utils.isBlank(dto.getCompanyName())) {
            throw new CustomException(Constants.COMPANY_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getLocation()) || Utils.isBlank(dto.getLocation())) {
            throw new CustomException(Constants.LOCATION_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);

        }
        if (Utils.isNull(dto.getIndustry()) || Utils.isBlank(dto.getIndustry())) {
            throw new CustomException(Constants.INDUSTRY_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getContactEmail()) || Utils.isBlank(dto.getContactEmail())) {
            throw new CustomException(Constants.CONTACT_EMAIL_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }
}
