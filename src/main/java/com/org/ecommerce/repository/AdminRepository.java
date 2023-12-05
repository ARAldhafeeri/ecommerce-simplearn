package com.org.ecommerce.repository;

import com.org.ecommerce.modal.Admin;

import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query("SELECT a FROM Admin a WHERE a.username =:username")
    Admin findByUsername(@Param("username") String username);
}
