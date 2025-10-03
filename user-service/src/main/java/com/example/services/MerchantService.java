package com.example.services;

import com.example.dto.requests.RegisterMerchantDTO;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    public boolean verifyKYC(RegisterMerchantDTO registerMerchantDTO) {
        return registerMerchantDTO.getMerchantName() != null && (!registerMerchantDTO.getMerchantName().trim().isEmpty() || registerMerchantDTO.getMerchantAddress() != null) && (!registerMerchantDTO.getMerchantAddress().trim().isEmpty() || registerMerchantDTO.getMerchantPhoneNumber() != null) && (!registerMerchantDTO.getMerchantPassword().isEmpty() || registerMerchantDTO.getBusinessRegNumber() != null) && !registerMerchantDTO.getBusinessRegNumber().trim().isEmpty();
    }
}
