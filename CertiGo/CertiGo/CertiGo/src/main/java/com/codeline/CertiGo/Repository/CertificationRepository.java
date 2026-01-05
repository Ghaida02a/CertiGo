package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certificate, Integer> {
    @Query("SELECT c FROM Certificate c WHERE c.isActive = true")
    List<Certificate> findAllActiveCertifications();

    @Query("SELECT c FROM Certificate c WHERE c.id = :id AND c.isActive = true")
    Certificate getCertificateById(Integer id);
}
