package com.org.ecommerce.service;

import com.org.ecommerce.modal.Admin;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {
    public String hashPassword(String password, String salt);
    public boolean verifyPassword(String password, String userSalt, String userHashedPassword);
    public Admin createAdmin(Admin admin);
    public Admin getAdminByUsername(String userName);
    public List<Admin> getAdmins();
    public UserDetails loadUserByUsername(String username);
    public String generateSalt();
    public void updateAdmin(Admin admin, int id);
}
