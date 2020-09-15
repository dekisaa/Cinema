package com.project.cinema.service;

import com.project.cinema.model.Cinema;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Projection;
import com.project.cinema.model.User;
import com.project.cinema.model.dto.CinemaDTO;
import com.project.cinema.repository.CinemaRepository;
import com.project.cinema.repository.HallRepository;
import com.project.cinema.repository.ProjectionRepository;
import com.project.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ProjectionRepository projectionRepository;

    public void save(Cinema cinema){
        cinemaRepository.save(cinema);
    }

    public List<Cinema> all(){
        return cinemaRepository.findAll();
    }

    public void create(CinemaDTO cinemaDTO){

        Optional<User> manager = userRepository.findByUsername(cinemaDTO.getManager());
        if(!manager.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji manager!");

        Cinema cinema = new Cinema();
        cinema.setName(cinemaDTO.getName());
        cinema.setAddress(cinemaDTO.getAddress());
        cinema.setEmail(cinemaDTO.getEmail());
        cinema.setPhone(cinemaDTO.getPhone());
        cinema.setManager(manager.get());
        save(cinema);

        manager.get().getCinema().add(cinema);
        userRepository.save(manager.get());
    }

    public void delete(Long id){
        Cinema cinema = findById(id);
        for(Hall h : cinema.getHalls()){
            h.setCinema(null);
            for(Projection p : h.getProjections())
                projectionRepository.delete(p);
            hallRepository.delete(h);
        }
        cinemaRepository.delete(cinema);
    }

    public void update(CinemaDTO cinema){
        Cinema cinema1 = findById(cinema.getId());

        Optional<User> manager = userRepository.findByUsername(cinema.getManager());
        if(!manager.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji manager!");

        cinema1.setName(cinema.getName());
        cinema1.setEmail(cinema.getEmail());
        cinema1.setAddress(cinema.getAddress());
        cinema1.setPhone(cinema.getPhone());

        if(manager.get() != cinema1.getManager()){

            cinema1.getManager().getCinema().remove(cinema1);
            userRepository.save(cinema1.getManager());
            cinema1.setManager(manager.get());
            manager.get().getCinema().add(cinema1);
            userRepository.save(manager.get());
        }

        save(cinema1);
    }

    public Cinema findById(Long id){
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        if(!cinema.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji bioskop!");
        return cinema.get();
    }

    public CinemaDTO cinemaDto(Long id){
        Cinema cinema = findById(id);
        return new CinemaDTO(cinema);
    }

    public List<Hall> halls(Long id){
        Cinema cinema = findById(id);
        return cinema.getHalls();
    }

    public List<Cinema> maganerCinemas(){
        User manager = userService.getCurrentUser();
        return manager.getCinema();
    }

    public List<Projection> allProjections(Long id){
        Cinema cinema = findById(id);
        List<Projection> projections = new ArrayList<>();
        for(Hall h : cinema.getHalls())
            projections.addAll(h.getProjections());

        return projections;
    }
}
