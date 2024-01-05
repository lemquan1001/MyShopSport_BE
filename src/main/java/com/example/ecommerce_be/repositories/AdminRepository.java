package com.example.ecommerce_be.repositories;

import com.example.ecommerce_be.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByLogin(String login);

    @Query(value ="SELECT p.* from admins p where p.user_name = ?1 ", nativeQuery = true)
    Optional<Admin> getAccountByUser(String id);

    @Query(value = "select b.* from admins b ", nativeQuery = true)
    List<Admin> getAllAdmin();
}
