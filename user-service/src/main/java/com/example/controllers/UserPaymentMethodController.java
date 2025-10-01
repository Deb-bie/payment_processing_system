package com.example.controllers;

import com.example.dto.requests.UserPaymentMethodDTO;
import com.example.dto.responses.ResponseHandler;
import com.example.services.UserPaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserPaymentMethodController {
    private final UserPaymentMethodService userPaymentMethodService;

    public UserPaymentMethodController (UserPaymentMethodService userPaymentMethodService) {
        this.userPaymentMethodService = userPaymentMethodService;
    }


    @PostMapping("/add-payment-method")
    public ResponseEntity<ResponseHandler<String>> addNewPaymentMethod(@RequestBody UserPaymentMethodDTO userPaymentMethodDTO) throws Exception {
        return userPaymentMethodService.addNewUserPaymentMethod(userPaymentMethodDTO);
    }
}
