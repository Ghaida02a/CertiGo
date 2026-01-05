package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c WHERE c.id =:id AND c.isActive = true")
    Company getCompanyById(Integer id);

    @Query("SELECT c FROM Company c WHERE c.isActive = true")
    List<Company> findAllActiveCompanies();
    @Query ("SELECT c FROM Company c WHERE c.companyName =:companyName AND c.isActive = true")
    Company getCompanyByName(String companyName);
}
