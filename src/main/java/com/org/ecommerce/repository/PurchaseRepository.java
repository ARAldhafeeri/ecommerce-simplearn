package com.org.ecommerce.repository;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.Purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query("SELECT p FROM Purchase AS p WHERE p.id = :id")
    public Purchase getPurchaseById(@Param("id") long id);

    @Query("SELECT p FROM Purchase AS p WHERE p.userId = :userId")
    public List<Purchase> getAllItemsByUserId(long userId);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(
        value="UPDATE Purchase AS p SET p.userId = :userId, p.total = :total, p.date = :date WHERE p.id = :id",
        nativeQuery = true)
    int  updatePurchase(
        @Param("id") long id, 
        @Param("userId") long userId, 
        @Param("total") BigDecimal total, 
        @Param("date") String date);
    
    @Query("DELETE FROM Purchase AS p WHERE p.id = :id")
    int deletePurchase(long id);

}

