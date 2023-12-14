package com.example.ecommerce_be.repositories;

import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    @Query(value ="SELECT p.* from bill_detail p", nativeQuery = true)
    List<BillDetail> getAllBillDetail();

    @Query(value ="SELECT p.* from bill_detail p where p.billid = ?1 ", nativeQuery = true)
    List<BillDetail> findByBillID(Long id);
}
