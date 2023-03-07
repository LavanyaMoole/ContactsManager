package com.econtactmanager.ContactManager.security;

import com.econtactmanager.ContactManager.model.Admin;
import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.AdminRepository;
import com.econtactmanager.ContactManager.repository.UserRepository;
import com.econtactmanager.ContactManager.security.AdminCustomUserDetails;
import com.econtactmanager.ContactManager.security.CustomUserDetails;
import com.econtactmanager.ContactManager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService  {
/*
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=adminService.getAdminByEmail(username);

        if(admin==null){
            throw new UsernameNotFoundException("could not found user!!!!");
        }
        AdminCustomUserDetails adminCustomUserDetails=new AdminCustomUserDetails(admin);
        return adminCustomUserDetails;
    }*/
}
