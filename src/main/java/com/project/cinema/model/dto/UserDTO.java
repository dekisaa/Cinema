package com.project.cinema.model.dto;

import com.project.cinema.model.Role;
import com.project.cinema.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDate birthDay;
    private String role;
    private Boolean active;

    public UserDTO(){

    }

    public UserDTO(User user){
        id = user.getId();
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        phone = user.getPhone();
        email = user.getEmail();
        birthDay = user.getBirthDay();
        role = user.getRoles().iterator().next().getName();
        active = user.isEnabled();
    }

    @Override
    public String toString() {
        return username + " " + password + " " + firstName + " " + lastName + " " + phone + " " + email + " " + birthDay + " " + role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
