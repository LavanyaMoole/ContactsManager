package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getByUserName(String email) {
       User user= userRepository.getUserByUserName(email);

       return user;
    }



}
