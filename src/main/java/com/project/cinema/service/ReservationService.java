package com.project.cinema.service;

import com.project.cinema.model.Movie;
import com.project.cinema.model.Projection;
import com.project.cinema.model.Reservation;
import com.project.cinema.model.User;
import com.project.cinema.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ProjectionService projectionService;

    @Autowired
    private MovieService   movieService;

    @Autowired
    private UserService userService;

    public void create(Long projectionId, Integer numOfCards){
        Reservation reservation = new Reservation();
        Projection projection = projectionService.findById(projectionId);

        int baseNumOfCards = projection.getNumOfReservedCards();
        projection.setNumOfReservedCards(numOfCards + baseNumOfCards);

        reservation.setCreated(LocalDateTime.now());
        reservation.setNumOfCards(numOfCards);
        reservation.setProjection(projection);
        reservation.setUser(userService.getCurrentUser());
        reservation.setBought(false);

        reservationRepository.save(reservation);
    }

    public List<Reservation> getAll(){
        List<Reservation> reservations = new ArrayList<>();

        for(Reservation reservation: reservationRepository.findAll()){
            if(reservation.getBought() == false){
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public Reservation findById(Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(!reservation.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji rezervacija!");
        }
        return reservation.get();
    }

    public void confirm(Long id){
        Reservation reservation = findById(id);
        Projection projection = reservation.getProjection();
        Movie movie = projection.getMovie();
        User user = userService.getCurrentUser();
        for(Movie m: user.getWatchedFilms()){
            System.out.println(m.getName());
        }
        movie.getViewers().add(user);
        user.getWatchedFilms().add(movie);
        userService.update(user);
        movieService.save(movie);


        reservation.setBought(true);
        save(reservation);
    }

    public void save(Reservation reservation){
        reservationRepository.save(reservation);
    }

    public void cancel(Long id){
        Reservation reservation = findById(id);
        reservationRepository.delete(reservation);
    }
}
