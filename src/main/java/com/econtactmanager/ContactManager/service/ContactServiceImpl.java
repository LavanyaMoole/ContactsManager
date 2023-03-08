package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Contact;
import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    //save contacts to the contact repository
    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
        log.info("contactServiceImpl saveContact() method called");
    }

    //get the list of all user contacts using userId of Contact
    @Override
    public List<Contact> getAllUserContactsByUserId(long userId) {
        log.info("contactServiceImpl getAllUserContactsByUserId() method called");
        return contactRepository.findContactsByUserId(userId);


    }

    //get the contact by contactId
    @Override
    public Optional<Contact> getContactById(long cId) {
        log.info("contactServiceImpl getContactById() method called");
      return  contactRepository.findById(cId);

    }

    //delete the contact from the list of contacts in User
    @Override
    public void deleteContact(User user, Contact contact) {
        log.info("contactServiceImpl deleteContact() method called");
     user.getContacts().remove(contact);

    }

    //count of number of contacts
    @Override
    public long numberOfContactsPresent() {
        log.info("contactServiceImpl numberOfContactsPresent() method called");
        return contactRepository.count();
    }

    //get all the contacts from contact Repository
    @Override
    public List<Contact> getAllContacts() {
        log.info("contactServiceImpl getAllContacts() method called");
        return contactRepository.findAll();
    }



}
