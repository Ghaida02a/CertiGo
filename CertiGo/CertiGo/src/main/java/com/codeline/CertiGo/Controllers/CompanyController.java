package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.CompanyCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.CompanyResponse;
import com.codeline.CertiGo.DTOUpdateRequest.CompanyUpdateRequest;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;


    @PostMapping("/create")
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyCreateRequestDTO requestObj) throws CustomException {
        CompanyCreateRequestDTO.validCreateCompanyRequest(requestObj);
        CompanyResponse createdCompany = companyService.saveCompany(requestObj);
        return ResponseEntity.status(201).body(createdCompany);
    }

    @GetMapping("/getAll")
    public List<CompanyResponse> getAllCompanies() {
        List<CompanyResponse> responseList = companyService.getAllCompanies();
        System.out.println(responseList);
        return responseList;
    }

    @GetMapping("/getById")
    public CompanyResponse getCompanyById(@RequestParam int id) throws CustomException {
        return companyService.getCompanyById(id);
    }
    @GetMapping("/getByName")
    public CompanyResponse getCompanyByName(@RequestParam String name) throws CustomException {
        return companyService.getCompanyByName(name);
    }

    @PutMapping("/Update")
    public CompanyResponse updateCompany(@RequestBody CompanyUpdateRequest updateObjFromUser) throws CustomException {
        CompanyUpdateRequest.validCreateCompanyRequest(updateObjFromUser);
        return companyService.updateCompany(updateObjFromUser);
    }

    @DeleteMapping("/delete")
    public String deleteCompany(@RequestParam int id) throws CustomException {
        companyService.deleteCompany(id);
        return "Company deleted successfully";
    }
}
