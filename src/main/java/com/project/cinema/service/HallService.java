package com.project.cinema.service;

import com.project.cinema.model.Cinema;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Projection;
import com.project.cinema.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CinemaService cinemaService;

    public void save(Hall hall, Long cinemaId){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Cinema cinema = cinemaService.findById(cinemaId);
        hall.setCinema(cinema);
        hallRepository.save(hall);
        cinemaService.save(cinema);
    }

    public void delete(Long id){
        Hall hall = findById(id);
        hall.getCinema().getHalls().remove(hall);
        cinemaService.save(hall.getCinema());
        hallRepository.delete(hall);

    }

    public void update(Hall hall){
        System.out.println("BBB");
        Hall hall1 = findById(hall.getId());
        hall1.setMark(hall.getMark());
        hall1.setCapacity(hall.getCapacity());
        hallRepository.save(hall1);
    }

    public Hall findById(Long id){
        Optional<Hall> hall = hallRepository.findById(id);
        if(!hall.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji sala!");
        return hall.get();
    }

    public List<Projection> projections(Long id, Date fromDate, Date endDate){
        //TODO
        return null;
    }
}
