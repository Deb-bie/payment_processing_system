package com.example.repositories;

import com.example.entities.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, UUID> {
}
