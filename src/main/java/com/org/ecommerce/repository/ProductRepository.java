package com.org.ecommerce.repository;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query("SELECT p FROM Product p WHERE p.id =:id")
    Product getProductById(@Param("id") long id);
    
    @Modifying
	@Query("DELETE FROM Product p WHERE p.id = :id")
	int deleteProduct(@Param("id") long id);
    
    @Modifying
    @Query(value ="UPDATE Product p SET p.name = :name, p.price = :price, p.dateAdded = :dateAdded, p.categoryId = :categoryId WHERE p.id = :id")
    int updateProduct(
        @Param("id") long id, 
        @Param("name") String name, 
        @Param("price") BigDecimal price, 
        @Param("dateAdded") String dateAdded, 
        @Param("categoryId") long categoryId
        );

}

