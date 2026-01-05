package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("SELECT p FROM Payment p WHERE p.isActive = true")
    List<Payment> findAllPayments();

    @Query("SELECT p FROM Payment p WHERE p.id = :id AND p.isActive = true")
    Payment getPaymentById(Integer id);

    @Query("SELECT p FROM Payment p WHERE p.isActive=true AND p.id IN (:id) ")
    List<Payment> getPaymentById(List<Integer> id);
}
