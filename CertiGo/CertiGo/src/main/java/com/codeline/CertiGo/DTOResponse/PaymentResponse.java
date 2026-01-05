package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Payment;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Enum.PaymentMethod;
import com.codeline.CertiGo.Enum.PaymentStatus;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Double amount;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private User user;
    private Course course;

    // Convert Entity → ResponseDTO
    public static PaymentResponse entityToDTOResponse(com.codeline.CertiGo.Entity.Payment entity) {
        return PaymentResponse.builder()
                .amount(entity.getAmount())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .paymentMethod(entity.getPaymentMethod())
                .paymentStatus(entity.getPaymentStatus())
                .user(entity.getUser())
                .course(entity.getCourse())
                .build();
    }

    // DTO Response -> Entity
    public static com.codeline.CertiGo.Entity.Payment convertDTOResponseToEntity(PaymentResponse dto) throws CustomException {
        if (Utils.isNull(dto)) {
            throw new CustomException(Constants.PAYMENT_IS_NULL, Constants.HTTP_STATUS_IS_NULL);
        }
        return Payment.builder()
                .amount(dto.getAmount())
                .isActive(dto.getIsActive())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .paymentMethod(dto.getPaymentMethod())
                .paymentStatus(dto.getPaymentStatus())
                .user(dto.getUser())
                .course(dto.getCourse())
                .build();
    }
}
