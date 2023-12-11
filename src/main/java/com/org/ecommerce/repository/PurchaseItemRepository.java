package com.org.ecommerce.repository;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.PurchaseItem;


import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Integer> {

    @Query("SELECT p FROM PurchaseItem p WHERE p.id = :id")
    PurchaseItem getPurchaseItemById(@Param("id") long id);

    @Query("SELECT p FROM PurchaseItem p WHERE p.purchaseId = :purchaseId")
	PurchaseItem getItemById(@Param("purchaseId") long purchaseId);
	
	@Query("SELECT p FROM PurchaseItem p WHERE p.userId = :id")
	List<PurchaseItem> getAllItemsByPurchaserId(@Param("id") long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value="UPDATE PurchaseItem p SET p.userId = :userId, p.productId = :productId, p.purchaseId = :purchaseId, p.rate = :rate, p.qty = :qty, p.price = :price WHERE p.id = :id")
	int updateItem(@Param("id") long id, @Param("userId") long userId, @Param("productId") long productId, @Param("purchaseId") long purchaseId, @Param("rate") BigDecimal rate, @Param("qty") int qty, @Param("price") BigDecimal price);
	
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("DELETE FROM PurchaseItem p WHERE p.id = :id")
	int deleteItem(@Param("id") long id);

    @Modifying
    @Query("DELETE FROM PurchaseItem p WHERE p.purchaseId = :purchaseId")
	int deleteAllItemsForPurchaseId(@Param("purchaseId") long purchaseId);
    

}

