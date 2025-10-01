package com.example.repositories;


import com.example.entities.UserPaymentMethod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPaymentMethodRepository extends CrudRepository<UserPaymentMethod, UUID> {
}
