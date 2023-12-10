package com.org.ecommerce.repository;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query("SELECT p FROM Product p WHERE p.id =:id")
    Product getProductById(@Param("id") long id);
    
    @Modifying(clearAutomatically=true)
    @Transactional
	@Query("DELETE FROM Product p WHERE p.id = :id")
	void deleteProduct(long id);

    List<Product> findAll();
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(
        value ="UPDATE Product p SET p.name = :name, p.price = :price, p.dateAdded = :dateAdded, p.categoryId = :categoryId WHERE p.id = :id")
    void updateProduct(
        @Param("id") long id, 
        @Param("name") String name, 
        @Param("price") BigDecimal price, 
        @Param("dateAdded") String dateAdded, 
        @Param("categoryId") long categoryId
        );

}

