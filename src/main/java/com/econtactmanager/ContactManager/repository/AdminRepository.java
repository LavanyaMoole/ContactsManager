package com.econtactmanager.ContactManager.repository;

import com.econtactmanager.ContactManager.model.Admin;
import com.econtactmanager.ContactManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {


    @Query("select a from Admin a where a.email= :email")
    public Admin getAdminByUserName(@Param("email") String email);

}
