package com.project.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ProjectionDTO {

    private Long id;
    private LocalDateTime date;
    private Double price;
    private Integer numOfReservedCards;
    private String hall;
    private String movie;

    public ProjectionDTO() {
    }

    @Override
    public String toString() {
        return id + " " + date + " " + price + " " +  numOfReservedCards + " " + hall + " " + movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumOfReservedCards() {
        return numOfReservedCards;
    }

    public void setNumOfReservedCards(Integer numOfReservedCards) {
        this.numOfReservedCards = numOfReservedCards;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
