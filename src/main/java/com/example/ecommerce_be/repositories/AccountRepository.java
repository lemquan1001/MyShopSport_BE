package com.example.ecommerce_be.repositories;

import com.example.ecommerce_be.entity.Account;
import com.example.ecommerce_be.entity.Customer;
import com.example.ecommerce_be.entity.Product_T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select a.* from account a WHERE a.user = ?1", nativeQuery = true)
    Account findByUsername(String user);
    @Query(value ="SELECT p.* from account p where p.user = ?1 ", nativeQuery = true)
    Optional<Account> getAccountByUser(String id);
}
