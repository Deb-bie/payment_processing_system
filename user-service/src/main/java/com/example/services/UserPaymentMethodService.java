package com.example.services;

import com.example.dto.requests.UserPaymentMethodDTO;
import com.example.dto.responses.ResponseHandler;
import com.example.entities.User;
import com.example.entities.UserPaymentMethod;
import com.example.enums.PaymentMethodStatus;
import com.example.exceptions.NotFoundException;
import com.example.repositories.UserPaymentMethodRepository;
import com.example.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserPaymentMethodService {
    private final UserRepository userRepository;
    private final UserPaymentMethodRepository userPaymentMethodRepository;

    public UserPaymentMethodService(UserRepository userRepository,
                                    UserPaymentMethodRepository userPaymentMethodRepository) {
        this.userRepository = userRepository;
        this.userPaymentMethodRepository = userPaymentMethodRepository;
    }

    public ResponseEntity<ResponseHandler<String>> addNewUserPaymentMethod(UserPaymentMethodDTO userPaymentMethodDTO) throws NotFoundException {
        Optional<User> user = userRepository.findById(userPaymentMethodDTO.getUserId());
        boolean isDefault  = !userPaymentMethodDTO.getIsDefault();

        if (user.isPresent()) {

            UserPaymentMethod newPaymentMethod = UserPaymentMethod.builder()
                    .user(user.get())
                    .paymentMethodName(userPaymentMethodDTO.getPaymentMethodName())
                    .paymentMethodType(userPaymentMethodDTO.getPaymentMethodType())
                    .lastFourDigits(userPaymentMethodDTO.getLastFourDigits())
                    .expiryYear(userPaymentMethodDTO.getExpiryYear())
                    .expiryMonth(userPaymentMethodDTO.getExpiryMonth())
                    .isDefault(isDefault)
                    .paymentMethodStatus(PaymentMethodStatus.ACTIVE)
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .updatedAt(new Timestamp(System.currentTimeMillis()))
                    .build();

            userPaymentMethodRepository.save(newPaymentMethod);

        } else {
            throw new NotFoundException("User not found");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseHandler.<String>builder()
                  .status(HttpStatus.CREATED)
                  .code(HttpStatus.CREATED.value())
                  .message("Payment Method Added")
                  .build()
        );
    }
}
