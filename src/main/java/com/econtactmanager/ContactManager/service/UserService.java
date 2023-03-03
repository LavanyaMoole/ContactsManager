package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface UserService {
    public User saveUser(User user);
    public User getByUserName(String email);

}
