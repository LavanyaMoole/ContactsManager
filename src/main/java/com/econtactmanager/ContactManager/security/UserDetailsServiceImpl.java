package com.econtactmanager.ContactManager.security;

import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.UserRepository;
import com.econtactmanager.ContactManager.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.getUserByUserName(username);

        if(user==null){
            throw new UsernameNotFoundException("could not found user!!!!");
        }
        CustomUserDetails customUserDetails=new CustomUserDetails(user);
        return customUserDetails;
    }
}
