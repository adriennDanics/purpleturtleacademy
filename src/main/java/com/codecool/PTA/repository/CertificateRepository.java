package com.codecool.PTA.repository;

import com.codecool.PTA.model.certificate.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    Certificate findByStudentId(Long id);
}
