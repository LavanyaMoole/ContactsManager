package com.econtactmanager.ContactManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="CONTACT")
public class Contact {
    public  Contact()
    {

    }

    public Contact( String name, String secondName, String work,
                   String email, String phone, String image, String description, User user) {

        this.name = name;
        this.secondName = secondName;
        this.work = work;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.description = description;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String secondName;
    @Column(nullable = false)
    private String work;
    @Column(unique = true,nullable = false,length = 40)
    private String email;
    @Column(unique = true,nullable = false,length = 11)
    private String phone;
    @Column
    private String image;
    @Column(length=5000)
    private String description;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        return this.cId==((Contact)obj).getcId();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
