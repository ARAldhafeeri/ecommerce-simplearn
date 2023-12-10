package com.org.ecommerce.repository;

import com.org.ecommerce.modal.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
    @Query("SELECT c FROM Category c WHERE c.id =:id")
    Category getCategoryById(@Param("id") long id);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("DELETE FROM Category c WHERE c.id = :id")
	int deleteCategory(@Param("id") long id);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value="UPDATE Category c SET c.name = :name WHERE c.id = :id", nativeQuery = true)
    int updCategory(@Param("id") long id, @Param("name") String name);
}

