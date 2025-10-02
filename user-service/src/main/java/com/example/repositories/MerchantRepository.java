package com.example.repositories;

import com.example.entities.Merchant;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MerchantRepository extends CrudRepository<Merchant, UUID> {
}
