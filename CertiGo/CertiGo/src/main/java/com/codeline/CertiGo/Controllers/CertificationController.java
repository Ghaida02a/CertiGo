package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.CertificateCreateRequest;
import com.codeline.CertiGo.DTOResponse.CertificateResponse;
import com.codeline.CertiGo.Entity.Certificate;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/certificates")
public class CertificationController {

    @Autowired
    private CertificateService certificateService;

    // Get all active certificates
    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        try {
            List<Certificate> certificates = certificateService.getAllCertificates();
            return ResponseEntity.ok(certificates);
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get certificate by ID
    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Integer id) {
        try {
            Certificate certificate = certificateService.getCertificateById(id);
            return ResponseEntity.ok(certificate);
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<CertificateResponse> createCertificate(@RequestBody CertificateCreateRequest certificateCreateRequest) {
        try {
            CertificateResponse createdCertificate = certificateService.saveCertificate(certificateCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCertificate);
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Delete certificate
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Integer id) {
        try {
            certificateService.deleteCertificate(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
