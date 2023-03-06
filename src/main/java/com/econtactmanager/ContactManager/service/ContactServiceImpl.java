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

    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllUserContactsByUserId(long userId) {
        return contactRepository.findContactsByUserId(userId);

    }

    @Override
    public Optional<Contact> getContactById(long cId) {
      return  contactRepository.findById(cId);

    }

    @Override
    public void deleteContact(User user, Contact contact) {
     user.getContacts().remove(contact);

    }

    @Override
    public void deleteUserContactById(long cId){
       Contact c= contactRepository.findById(cId).get();
       contactRepository.delete(c);

    }

    @Override
    public long numberOfContactsPresent() {
        return contactRepository.count();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }



}
