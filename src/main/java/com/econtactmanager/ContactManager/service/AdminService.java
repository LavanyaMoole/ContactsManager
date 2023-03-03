package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    public Admin saveAdmin(Admin admin);

    public Admin getAdminByEmail(String email);
}
