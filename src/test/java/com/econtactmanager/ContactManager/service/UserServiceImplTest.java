package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.ContactManagerApplication;
import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = ContactManagerApplication.class)
class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    void saveUser() {
        User user1=new User();
        user1.setEmail("lavanya@gmail.com");
        if(userRepository.getUserByUserName(user1.getEmail())==null){
            userService.saveUser(user1);
        }
        else{
            throw new RuntimeException("User already exists!!!");
        }


    }

    @ParameterizedTest
    @ValueSource(strings = {"suresh@gmail.com"})
    void getByUserName(String expectedEmail) {
       String actualEmail= userService.getByUserName(expectedEmail).getEmail();
       assertEquals(expectedEmail,actualEmail);
    }

}