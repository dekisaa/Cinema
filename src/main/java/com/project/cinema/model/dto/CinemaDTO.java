package com.project.cinema.model.dto;

import com.project.cinema.model.Cinema;

public class CinemaDTO {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String manager;

    public CinemaDTO() {
    }

    public CinemaDTO(Cinema cinema){
        this.id = cinema.getId();
        this.name = cinema.getName();
        this.address = cinema.getAddress();
        this.phone = cinema.getPhone();
        this.email = cinema.getEmail();
        this.manager = cinema.getManager().getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
