package com.example.ecommerce_be.repositories;

import com.example.ecommerce_be.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    @Query(value = "select b.* from brands b ", nativeQuery = true)
    List<Brand> getAllBrand();

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Brand s WHERE s.brandName = :brandName")
    boolean existsByBrand(String brandName);
}
