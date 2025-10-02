package com.example.entities;

import com.example.enums.MerchantAccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="merchant")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID merchantId;

    @Column(nullable = false)
    private String merchantName;

    @Column(nullable = false)
    private String merchantEmail;

    private String merchantAddress;

    private String merchantPhone;

    @Column(nullable = false)
    private String merchantPassword;

    private String businessRegNumber;

    private MerchantAccountStatus merchantAccountStatus;

    private String merchantAPIKey;

    @OneToMany(mappedBy="merchant", cascade= CascadeType.ALL)
    private List<Transactions> transactions;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SettlementAccount> settlementAccounts;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

}
