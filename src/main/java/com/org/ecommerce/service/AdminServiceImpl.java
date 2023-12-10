package com.org.ecommerce.service;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.repository.AdminRepository;

import jakarta.transaction.Transactional;

import org.apache.commons.lang3.CharSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;


    @Override
    public boolean verifyPassword(String password, String userSalt, String userHashedPassword) {
        // one way verify password hash function
        String hashedPassword = hashPassword(password, userSalt);
        return hashedPassword.equals(userHashedPassword);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public String generateSalt() {
      try {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16]; // 16 bytes is a common size for salts
        random.nextBytes(saltBytes);

        // Convert the salt to a hexadecimal string
        String salt = new BigInteger(1, saltBytes).toString(16);
        return salt;
      } catch (Exception e){
          System.out.println("error in generateSalt method");
          return null;
      }
    }

    @Override
    public String hashPassword(String password, String salt) {
       try{
           int iterations = 10000; // Number of iterations for the key derivation function
           int keyLength = 256; // Key length in bits
            byte[] byteSalt = new BigInteger(salt, 16).toByteArray();
            // Convert the salt to a hexadecimal string
           PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), byteSalt, iterations, keyLength);
           SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
           byte[] hash = skf.generateSecret(spec).getEncoded();

           // Convert the hash to a Base64-encoded string
           return Base64.getEncoder().encodeToString(hash);
       } catch (Exception e)  {
           System.out.println("error in hashPassword method");
           return null;
        }
    }



    @Override
    public Admin getAdminByUsername(String userName) {
        return adminRepository.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Admin user = adminRepository.findByUsername(email);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getHasedPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }

    @Override
    public void updateAdmin(Admin admin, int id) {
        adminRepository.findById(id)
        .ifPresent(newAdmin -> {
            newAdmin.setUsername(admin.getUsername());
            newAdmin.setEmail(admin.getEmail());
            newAdmin.setHasedPassword(admin.getHasedPassword());
            newAdmin.setSalt(admin.getSalt());
            adminRepository.save(newAdmin);
        });
    }

}
