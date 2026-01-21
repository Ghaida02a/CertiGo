package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Company;
import com.codeline.CertiGo.Entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyResponse {
    private Integer companyId;
    private String companyName;
    private String location;
    private String industry;
    private String contactEmail;

    // Convert Entity → DTO
    public static CompanyResponse fromEntity(Company company) {
        return CompanyResponse.builder()
                .companyId(company.getId())
                .companyName(company.getCompanyName())
                .location(company.getLocation())
                .industry(company.getIndustry())
                .contactEmail(company.getContactEmail())
                .build();
    }
}
