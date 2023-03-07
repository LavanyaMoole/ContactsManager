package com.econtactmanager.ContactManager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@Order(2)
public class AdminSecurity {
/*
    @Autowired
    private AdminDetailsService adminDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder1(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider1(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(adminDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder1());
        return daoAuthenticationProvider;
    }
    @Bean
    protected SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/adminLogin")
                .defaultSuccessUrl("/admin/adminDashboard")
                .and().csrf().disable();
        return http.build();
    }*/

}
