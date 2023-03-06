package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    //save admin
    public Admin saveAdmin(Admin admin);

    //get admin by user
    public Admin getAdminByEmail(String email);
}
