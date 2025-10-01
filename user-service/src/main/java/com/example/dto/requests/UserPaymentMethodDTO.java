package com.example.dto.requests;

import com.example.enums.PaymentMethodStatus;
import com.example.enums.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPaymentMethodDTO {
    private UUID userId;
    private PaymentMethodType paymentMethodType;
    private String paymentMethodName;
    private String lastFourDigits;
    private int expiryYear;
    private int expiryMonth;
    private Boolean isDefault;
    private PaymentMethodStatus paymentMethodStatus;
}
