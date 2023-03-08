package com.econtactmanager.ContactManager.security;

import org.springframework.stereotype.Service;

@Service
public class AdminDetailsServiceImpl {
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
