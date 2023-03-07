package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Contact;
import com.econtactmanager.ContactManager.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface ContactService {

    //saving contact
    public void saveContact(Contact contact);

    //get all the contacts of the user
    public List<Contact> getAllUserContactsByUserId(long userId);

    //get contact by Id
    public Optional<Contact> getContactById(long cId);

    //delete contact from user contact list
    public void deleteContact(User user, Contact contact);

    //total number of contacts
    public long numberOfContactsPresent();

    //get all the contacts from contact Repository
    public List<Contact> getAllContacts();
}
