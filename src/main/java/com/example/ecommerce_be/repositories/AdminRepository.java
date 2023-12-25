package com.example.ecommerce_be.repositories;

import com.example.ecommerce_be.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByLogin(String login);
}
