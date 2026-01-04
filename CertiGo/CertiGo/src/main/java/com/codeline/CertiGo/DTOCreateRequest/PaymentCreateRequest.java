package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Payment;
import com.codeline.CertiGo.Enum.PaymentMethod;
import com.codeline.CertiGo.Enum.PaymentStatus;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCreateRequest {
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private UserCreateRequest user;
    private CourseCreateRequest course;

    //Validation
    public static void validatePaymentCreateRequested(PaymentCreateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getAmount())) {
            throw new CustomException(Constants.PAYMENT_AMOUNT_IS_NULL, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getPaymentMethod())) {
            throw new CustomException(Constants.PAYMENT_PAYMENT_METHODS_IS_NULL, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getPaymentStatus())) {
            throw new CustomException(Constants.PAYMENT_PAYMENT_STATUS_IS_NULL, Constants.HTTP_STATUS_IS_NULL);
        }
    }

    // Convert DTO → Entity
    public static Payment convertDTOToEntity(PaymentCreateRequest dto) {
        Payment payment = Payment.builder()
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .paymentStatus(dto.getPaymentStatus())
                .build();
        return payment;
    }

    // Convert Entity → DTO
    public static PaymentCreateRequest convertEntityToDTO(Payment entity) {
        return PaymentCreateRequest.builder()
                .amount(entity.getAmount())
                .paymentMethod(entity.getPaymentMethod())
                .paymentStatus(entity.getPaymentStatus())
                .build();
    }
}
