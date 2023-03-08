package com.econtactmanager.ContactManager.service;

import com.econtactmanager.ContactManager.model.Admin;
import com.econtactmanager.ContactManager.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;

    //save admin
    @Override
    public Admin saveAdmin(Admin admin) {
        adminRepository.save(admin);
        log.info("AdminServiceImpl saveAdmin() method called ");
        return admin;
    }

    //get admin by admin email
    @Override
    public Admin getAdminByEmail(String email) {
        log.info("AdminServiceImpl getAdminByEmail() method called ");
        return adminRepository.getAdminByUserName(email);
    }
}
