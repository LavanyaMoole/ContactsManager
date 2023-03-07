package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Admin;
import com.econtactmanager.ContactManager.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;

    //save admin
    @Override
    public Admin saveAdmin(Admin admin) {
        adminRepository.save(admin);
        return admin;
    }

    //get admin by admin email
    @Override
    public Admin getAdminByEmail(String email) {
       return adminRepository.getAdminByUserName(email);
    }
}
