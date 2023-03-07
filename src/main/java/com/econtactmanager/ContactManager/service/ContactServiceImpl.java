package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Contact;
import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    //save contacts to the contact repository
    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    //get the list of all user contacts using userId of Contact
    @Override
    public List<Contact> getAllUserContactsByUserId(long userId) {
        return contactRepository.findContactsByUserId(userId);

    }

    //get the contact by contactId
    @Override
    public Optional<Contact> getContactById(long cId) {
      return  contactRepository.findById(cId);

    }

    //delete the contact from the list of contacts in User
    @Override
    public void deleteContact(User user, Contact contact) {
     user.getContacts().remove(contact);

    }

    //count of number of contacts
    @Override
    public long numberOfContactsPresent() {
        return contactRepository.count();
    }

    //get all the contacts from contact Repository
    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }



}
