package com.econtactmanager.ContactManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminController {

    @GetMapping("/adminDashboard")
    public String dashBoard(Model model, Principal principal){

        model.addAttribute("title","dashboard e-Contact Manager");
        return "adminDashboard";
    }

}
