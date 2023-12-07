package com.org.ecommerce.service;

import java.util.List;

import com.org.ecommerce.modal.User;

public interface UserServices {

    public User authenticate(String userId, String pwd);
    public User getUserById(long id);
    public User getUserByEmailId(String emailId);
    
    public int updateUser(User user);
    public List<User> getAllUsers();
    public User createUser(User user);
}
