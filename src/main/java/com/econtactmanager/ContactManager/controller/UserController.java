package com.econtactmanager.ContactManager.controller;

import com.econtactmanager.ContactManager.helper.Message;
import com.econtactmanager.ContactManager.model.Contact;
import com.econtactmanager.ContactManager.model.User;
import com.econtactmanager.ContactManager.repository.ContactRepository;
import com.econtactmanager.ContactManager.repository.UserRepository;
import com.econtactmanager.ContactManager.service.ContactService;
import com.econtactmanager.ContactManager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void commonData(Model model, Principal principal)
    {
        String userName=principal.getName();
        System.out.println("username "+userName);
        User user= userService.getByUserName(userName);
        System.out.println("User "+user);
        model.addAttribute("user",user);

    }

//dashboard page
    @GetMapping("/dashboard")
    public String dashBoard(Model model, Principal principal)
    {
        model.addAttribute("title","dashboard e-Contact Manager");
        log.info("user dashboard");
        return "/user/dashboard";
    }

    //Add contact form for adding user contacts
    @GetMapping("/add-contact")
    public String addContactForm(Model model)
    {
        model.addAttribute("title","Add-Contact-Form");
        model.addAttribute("contact",new Contact());
        log.info("add contact method executed");
        return "/user/addContactForm";


    }

    //processing add contact form method when the add contact form submitted
    @PostMapping("/process_contact")
    public String processingContact(@ModelAttribute Contact contact,
                                    @RequestParam("profileImage") MultipartFile file
                                    , Principal principal, HttpSession session)
    {


        try
        {
            String name=principal.getName();
            User user=userService.getByUserName(name);
            //profile uploading for contact
            if(file.isEmpty())
            {
                //if nothing is set , default image is set
                contact.setImage("contact.png");
                log.info("If the file is empty, it will set the image to contact.png");

            }
            else
            {
                //setting the selected profile for contact using file
                contact.setImage(file.getOriginalFilename());
                File file1=new ClassPathResource("static/img").getFile();
              Path path=  Paths.get(file1.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                log.info("Image Uploaded successfully");
            }
            contact.setUser(user);
            user.getContacts().add(contact);
            userService.saveUser(user);
            System.out.println("Contact Data"+contact);
            log.info("contact added to database.");
            log.info("process contact method executed");

            session.setAttribute("message",new Message("your contact added!!!","success"));

        }
        catch(Exception e)
        {
            e.printStackTrace();
            session.setAttribute("message",new Message("something went wrong!!","danger"));
        }
        return "/user/addContactForm";

    }

    //Get List of all contacts of user
    @GetMapping("/view_contacts")
    public String viewContacts(Model model,Principal principal)
    {
        model.addAttribute("title","view all your contacts");

        String userName=principal.getName();
        User user=userService.getByUserName(userName);

       List<Contact> contacts= contactService.getAllUserContactsByUserId(user.getId());
       model.addAttribute("contacts",contacts);
       log.info("List of user contacts to view");
        return "/user/viewContacts";
    }

    //display particular contact of user
    @GetMapping("/contact/{cId}")
    public String displayParticularContact(@PathVariable("cId") Long cId,Model model,
                                           Principal principal)
    {
        Optional<Contact> contact=contactService.getContactById(cId);
       Contact c= contact.get();

       String userName=principal.getName();
       User user=userService.getByUserName(userName);
       //if userId and contact userId is same
       if(user.getId()==c.getUser().getId())
       {
       model.addAttribute("contact1",c);
       model.addAttribute("title",c.getName());
       }
       log.info("contact with"+c.getcId()+ "of user:"+user.getEmail()+" is displayed");
       return "/user/contactDetail";
    }

    //delete the particular contact with Id
    @GetMapping("/deleteContact/{cId}")
    public String deleteContact(@PathVariable("cId") long cId,Model model,
                                HttpSession session,Principal principal)
    {
       String userName=principal.getName();
        User user=userService.getByUserName(userName);
       Contact contact= contactRepository.findById(cId).get();
        //if userId and contact userId is same then remove the contact from user contacts list
       if(user.getId()==contact.getUser().getId())
       {
           contactService.deleteContact(user,contact);
           user.getContacts().remove(contact);
           userService.saveUser(user);
           log.info("User Id "+user.getId()+" and contact's user Id "+contact.getUser().getId()+" matched");
           log.info("contact with "+contact.getcId()+" of user is deleted");
       }
       session.setAttribute("message",new Message("Contact deleted successfully!!!","success"));
        return "/user/viewContacts";

    }
    //updating particular contact of user
    @PostMapping("/updateContact/{cId}")
    public String UpdateContact(@PathVariable("cId") Long cId, Model model)
    {
        model.addAttribute("title","Update contact form");
        Optional<Contact> optContact=contactService.getContactById(cId);
        Contact contact=optContact.get();
        model.addAttribute("contact",contact);
        log.info("Contact for update");
        return "/user/updateContactForm";

    }

    //processing or saving the updated contact
    @PostMapping("/processUpdatedContact")
    public String processUpdateContact(@ModelAttribute Contact contact,
                                       @RequestParam("profileImage") MultipartFile multipartFile,
                                       Model model,Principal principal,HttpSession session)
    {

        try{

            Contact oldContactDetail= contactService.getContactById(contact.getcId()).get();
            if(!multipartFile.isEmpty())
            {
                //file have to  rewrite
                //delete the photo first and then update new profile
                File fileToBeDeleted=new ClassPathResource("static/img").getFile();
                File oldFile=new File(fileToBeDeleted,oldContactDetail.getImage());
                oldFile.delete();

                //updating new profile
                File file1=new ClassPathResource("static/img").getFile();
                Path path=  Paths.get(file1.getAbsolutePath()+File.separator+multipartFile.getOriginalFilename());
                Files.copy(multipartFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                contact.setImage(multipartFile.getOriginalFilename());
            }
            else
            {
                contact.setImage(oldContactDetail.getImage());
            }
            String userName= principal.getName();
            User user= userService.getByUserName(userName);
            contact.setUser(user);
            contactService.saveContact(contact);
            log.info("Contact with "+contact.getcId()+" for updated successfully!!!");
            session.setAttribute("message",new Message("Contact Updated successfully!!!","success"));
        }
        catch (Exception e)
        {

        }
        return "redirect:/user/contact/"+contact.getcId();
    }

    //view user profile
    @GetMapping("/viewProfile")
    public String ViewProfile(Model model,Principal principal)
    {
        String userName=principal.getName();
        User user=userService.getByUserName(userName);
        model.addAttribute("user",user);
        model.addAttribute("title","user profile page");
        log.info("user viewed profile");
        return "viewProfilePage";

    }

}
