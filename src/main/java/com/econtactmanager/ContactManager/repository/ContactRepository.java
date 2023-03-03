package com.econtactmanager.ContactManager.repository;

import com.econtactmanager.ContactManager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    @Query("from Contact as c where c.user.id= :id")
    public List<Contact> findContactsByUserId(@Param("id") long userId);




    @Modifying
    @Transactional
    @Query("DELETE Contact c WHERE c.cId = ?1")
    void deleteByContactId(long cId);

}
