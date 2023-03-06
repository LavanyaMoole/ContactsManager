package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.ContactManagerApplication;
import com.econtactmanager.ContactManager.model.Contact;
import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ContactManagerApplication.class)
class ContactServiceImplTest {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
   public ContactService contactService;

    @Test
    public void getAllContacts()
    {
        List<Contact> contacts=contactService.getAllContacts();
        Long noOfContacts=contactService.numberOfContactsPresent();
        assertEquals(contacts.size(),noOfContacts);
    }

    //here the test compares total contacts vs user contacts
    @ParameterizedTest
    @ValueSource(strings = {"suresh@gmail.com"})
    public void getAllUserContacts(String email){
        User user=new User();
        user.setEmail(email);
       long id= user.getId();
       List<Contact> noOfUserContacts= contactService.getAllUserContactsByUserId(id);
        List<Contact> allContacts=contactService.getAllContacts();
        assertNotEquals(noOfUserContacts.size(),allContacts.size());

    }



}