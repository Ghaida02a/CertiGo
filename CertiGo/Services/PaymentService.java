package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.CourseCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.PaymentCreateRequest;
import com.codeline.CertiGo.DTOCreateRequest.UserCreateRequest;
import com.codeline.CertiGo.DTOResponse.PaymentResponse;
import com.codeline.CertiGo.Entity.Payment;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    // Get all active payments
    public List<Payment> getAllPayments() throws CustomException {
        List<Payment> payments = paymentRepository.findAllPayments();
        if (Utils.isListNotEmpty(payments)) {
            return payments;
        } else {
            throw new CustomException(Constants.PAYMENT_LIST_IS_NULL, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    // Get payment by ID
    public Payment getPaymentById(int id) throws CustomException {
        Payment paymentOpt = paymentRepository.getPaymentById(id);
        if (Utils.isNotNull(paymentOpt)) {
            return paymentOpt;
        } else {
            throw new CustomException(Constants.PAYMENT_IS_NULL, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    //Delete payment
    public void deletePayment(Integer id) throws CustomException {
        Payment existingOpt = paymentRepository.getPaymentById(id);

        if (Utils.isNotNull(existingOpt)) {
            existingOpt.setUpdatedAt(new Date());
            existingOpt.setIsActive(Boolean.FALSE);
            paymentRepository.save(existingOpt);
        } else {
            throw new CustomException(Constants.PAYMENT_IS_NULL, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    // Save payment
    public PaymentResponse savePayment(PaymentCreateRequest paymentCreateRequest) throws CustomException {
        // Validate request
        PaymentCreateRequest.validatePaymentCreateRequested(paymentCreateRequest);

        Payment payment = PaymentCreateRequest.convertDTOToEntity(paymentCreateRequest);
        payment.setCreatedAt(new Date());
        payment.setIsActive(Boolean.TRUE);

        // Set user and course if provided
        if (Utils.isNotNull(paymentCreateRequest.getUser())) {
            User userEntity = UserCreateRequest.convertDTOToEntity(paymentCreateRequest.getUser());
            payment.setUser(userEntity);
        }

        if (Utils.isNotNull(paymentCreateRequest.getCourse())) {
            Course courseEntity = CourseCreateRequest.convertToCourse(paymentCreateRequest.getCourse());
            payment.setCourse(courseEntity);
        }
        Payment savedPayment = paymentRepository.save(payment);
        return PaymentResponse.entityToDTOResponse(savedPayment);
    }
}
