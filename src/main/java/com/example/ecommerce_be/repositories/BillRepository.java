package com.example.ecommerce_be.repositories;

import com.example.ecommerce_be.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(value ="SELECT p.* from bill p where p.status=0", nativeQuery = true)
    List<Bill> getAllBill();
    @Query(value ="SELECT p.* from bill p where p.email = ?1 ", nativeQuery = true)
    List<Bill> getBillByEmail(String id);

    @Query(value ="SELECT b.* from bill b where b.status = 0 ", nativeQuery = true)
    List<Bill> getBillByStatus0();

    @Query(value ="SELECT b.* from bill b where b.status = 1 ", nativeQuery = true)
    List<Bill> getBillByStatus1();

    @Query(value ="SELECT b.* from bill b where b.status = 2 ", nativeQuery = true)
    List<Bill> getBillByStatus2();

    @Query(value ="SELECT b.* from bill b where b.status = 3 ", nativeQuery = true)
    List<Bill> getBillByStatus3();

    @Query(value ="SELECT c.brand,c.description,c.product_name,b.size,a.quantify,c.price from bill_detail a,product_details b,products c where a.product_detail_id = b.id and b.product_code =c.id and a.billid = :id ", nativeQuery = true)
    List<Bill> getInfoProOfCusById(Long id);
//    @Query(value ="SELECT p.* from products p where p.id = ?1", nativeQuery = true)
//    Optional<Product_T> getProduct_ById(Long id);
//    @Query(value ="SELECT p.* from products p where p.product_name like %?1%", nativeQuery = true)
//    List<Product_T> getProduct_ByName(String productName);
//   @Query(value ="SELECT p.* from products p where p.category_id =?1", nativeQuery = true)
//   List<Product_T> getAllProduct_ByCategory(Category category);



}
