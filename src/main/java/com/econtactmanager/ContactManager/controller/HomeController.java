package com.econtactmanager.ContactManager.controller;

import com.econtactmanager.ContactManager.helper.Message;
import com.econtactmanager.ContactManager.model.Admin;
import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.UserRepository;
import com.econtactmanager.ContactManager.service.AdminService;
import com.econtactmanager.ContactManager.service.UserService;
import com.econtactmanager.ContactManager.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@Slf4j
public class HomeController
{
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    //home page
    @RequestMapping("/")
    public String home(Model model)
    {
        model.addAttribute("title","Home e-Contact Manager");
        return "home";
    }
    //About page
    @RequestMapping("/about")
    public String about(Model model)
    {
        model.addAttribute("title","About e-Contact Manager");
        return "about";
    }
    //user signup page
    @RequestMapping("/signup")
    public String signUp(Model model)
    {
        model.addAttribute("title","Signup e-Contact Manager");
        model.addAttribute("user",new User());
        log.info("user signup form");
        return "signup";
    }
    //saving user details after registration
@PostMapping("/do_register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value = "agreement",defaultValue = "false") boolean agreement,
                               Model model,
                               HttpSession session,
                               @RequestParam("profileImage") MultipartFile file)
{

        String email="admin@gmail.com";
Admin admin=adminService.getAdminByEmail(email);


    try
    {
        if(file.isEmpty())
        {

            user.setImageUrl("contact.png");

        }
        else
        {
            user.setImageUrl(file.getOriginalFilename());
            File file1=new ClassPathResource("static/img").getFile();
            Path path=  Paths.get(file1.getAbsolutePath()+File.separator+file.getOriginalFilename());
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image Uploaded");
        }
        //for terms and conditions in registration page
        if(!agreement)
        {
            System.out.println("you have not agreed terms and conditions");
            throw new Exception("you have not agreed terms and conditions");

        }
        user.setRole("ROLE_USER");

       user.setAdmin(admin);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //if the terms and conditions are checked
        System.out.println("Agreement"+agreement);
        System.out.println("User"+user);
        User result=userService.saveUser(user);
        model.addAttribute("user",new User());
        session.setAttribute("message",new Message("Successfully Registered!!","alert-success"));
        return "signup";

    }catch(Exception e)
    {
        e.printStackTrace();
        model.addAttribute("user",user);
        session.setAttribute("message",new Message("something went wrong!! "+e.getMessage(),"alert-danger"));
        return "signup";
    }
    }
//user login
    @GetMapping("/login")
    public String userLogin(Model model)
    {
        model.addAttribute("title","user login Contact Manager");
        return "login";

    }
//admin login
    @GetMapping("/adminLogin")
    public String adminLogin(Model model)
    {
        model.addAttribute("title","Admin login Contact Manager");
        return "adminLogin";

    }
    //admin signup page
    @RequestMapping("/adminSignup")
    public String adminSignUp(Model model)
    {
        model.addAttribute("title","Signup e-Contact Manager");
        model.addAttribute("admin",new Admin());
        return "adminSignup";
    }
//saving admin details after registration
   @PostMapping("/AdminRegisterProcess")
    public String registerAdmin(@ModelAttribute("admin") Admin admin,
                                @RequestParam(value = "agreement",defaultValue = "false") boolean agreement,
                                Model model,
                               HttpSession session,
                               @RequestParam("profileImage") MultipartFile file)
   {

        try {
            if(file.isEmpty()){

                admin.setImageUrl("contact.png");

            }
            else{
                admin.setImageUrl(file.getOriginalFilename());
                File file1=new ClassPathResource("static/img").getFile();
                Path path=  Paths.get(file1.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image Uploaded");
            }
            if(!agreement){
                System.out.println("you have not agreed terms and conditions");
                throw new Exception("you have not agreed terms and conditions");

            }
            admin.setRole("ROLE_ADMIN");


            admin.setPassword(passwordEncoder.encode(admin.getPassword()));

            System.out.println("Agreement"+agreement);
            System.out.println("Admin"+admin);
            Admin result=adminService.saveAdmin(admin);
            model.addAttribute("user",new User());
            session.setAttribute("message",new Message("Successfully Registered!!","alert-success"));

            return "signup";

        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("user",admin);
            session.setAttribute("message",new Message("something went wrong!! "+e.getMessage(),"alert-danger"));
            return "signup";
        }


    }



}


