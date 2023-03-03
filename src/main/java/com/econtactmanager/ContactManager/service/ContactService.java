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

    public void saveContact(Contact contact);

    public List<Contact> getAllUserContactsByUserId(long userId);

    public Optional<Contact> getContactById(long cId);

    public void deleteContact(User user, Contact contact);

    public void deleteUserContactById(long cId);
}
