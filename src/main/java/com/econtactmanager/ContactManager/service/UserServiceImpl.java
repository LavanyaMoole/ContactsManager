package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    //saving the user to the repository
    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        log.info("UserServiceImpl saveUser() method called ");
        return user;
    }

    //get user from repository using UserEmail
    @Override
    public User getByUserName(String email) {
       User user= userRepository.getUserByUserName(email);
        log.info("UserServiceImpl getByUserName() method called ");

       return user;
    }



}
