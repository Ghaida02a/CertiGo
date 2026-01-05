package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.PaymentCreateRequest;
import com.codeline.CertiGo.DTOResponse.PaymentResponse;
import com.codeline.CertiGo.Entity.Payment;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Get all active payments
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        try {
            Payment payment = paymentService.getPaymentById(id);
            return ResponseEntity.ok(payment);
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Create new payment
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentCreateRequest paymentCreateRequest) {
        try {
            PaymentResponse createdPayment = paymentService.savePayment(paymentCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Delete payment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.noContent().build();
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}