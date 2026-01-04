package com.codeline.CertiGo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeline.CertiGo.Repository.CompanyRepository;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;



}
