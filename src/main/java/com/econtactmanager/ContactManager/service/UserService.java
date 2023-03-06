package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface UserService {

    //saving user
    public User saveUser(User user);

    //getting user by email
    public User getByUserName(String email);

}
