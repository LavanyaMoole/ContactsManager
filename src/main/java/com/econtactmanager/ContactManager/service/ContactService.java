package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Contact;
import com.econtactmanager.ContactManager.model.User;
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


    public void deleteUserContactById(long cId);

    public long numberOfContactsPresent();

    public List<Contact> getAllContacts();
}
