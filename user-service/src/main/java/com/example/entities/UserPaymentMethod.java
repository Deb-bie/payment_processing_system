package com.example.entities;


import com.example.enums.PaymentMethodStatus;
import com.example.enums.PaymentMethodType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID userPaymentMethodId;

    private String paymentMethodName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private PaymentMethodType paymentMethodType;

    private String lastFourDigits;

    private int expiryYear;

    private int expiryMonth;

    private Boolean isDefault;

    @Column(nullable = false)
    private PaymentMethodStatus paymentMethodStatus;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;
}
