package com.org.ecommerce.repository;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.User;

import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.emailId = :emailId and u.pwd = :pwd")
	User authenticate(@Param("emailId") String emailId, @Param("pwd") String pwd);

	@Query("SELECT u FROM User u WHERE u.id = :id")
	User getUserById(long id);


    @Query("SELECT u FROM User u WHERE u.emailId = :emailId")
	User getUserByEmailId(@Param("emailId") String emailId);

	@Modifying
	@Query(
		value ="UPDATE User u SET u.fname = :fname, u.lname = :lname, u.address = :address, u.age = :age, u.dateAdded = :dateAdded, u.emailId = :emailId, u.pwd = :pwd WHERE u.ID = :ID",
		nativeQuery = true)
	int updatUser(
		@Param("ID") long id,
		@Param("fname") String fname,
		@Param("lname") String lname,
		@Param("address") String address,
		@Param("age") int age,
		@Param("dateAdded") java.util.Date dateAdded,
		@Param("emailId") String emailId,
		@Param("pwd") String pwd
		);
}

