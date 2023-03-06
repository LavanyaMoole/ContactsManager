package com.econtactmanager.ContactManager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class User {
    public User()
    {
        super();
    }

    public User( String email, String name, String password, String role,
                boolean enabled, String imageUrl, List<Contact> contacts, String about) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.imageUrl = imageUrl;
        this.contacts = contacts;
        this.about = about;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@Column(unique = true)
    private String email;
@Column
    private String name;
@Column
    private String password;

@Column
private String role;
    @Column
    private boolean enabled;
    @Column
    private String imageUrl;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy ="user",orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();
    @Column(length = 500)
    private String about;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @ManyToOne
    private Admin admin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
