package com.org.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ecommerce.modal.User;
import com.org.ecommerce.repository.UserRepository;

@Service
public class UserServicesImpl implements UserServices {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public User authenticate(String userId, String pwd){ 
        return userRepository.authenticate(userId, pwd);
    }

    @Override
    public User getUserById(long id){
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByEmailId(String emailId){
        return userRepository.getUserByEmailId(emailId);
    }
    
    @Override
    public int updateUser(User user){
        return userRepository.updatUser(
            user.getID(),
            user.getFname(),
            user.getLname(),
            user.getAddress(),
            user.getAge(),
            user.getDateAdded(),
            user.getEmail(),
            user.getPwd()
            );
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }
}
