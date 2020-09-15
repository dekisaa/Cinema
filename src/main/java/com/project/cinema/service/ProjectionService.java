package com.project.cinema.service;

import com.project.cinema.model.Hall;
import com.project.cinema.model.Movie;
import com.project.cinema.model.Projection;
import com.project.cinema.model.Reservation;
import com.project.cinema.model.dto.ProjectionDTO;
import com.project.cinema.repository.HallRepository;
import com.project.cinema.repository.MovieRepository;
import com.project.cinema.repository.ProjectionRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectionService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ProjectionRepository projectionRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private MovieRepository movieRepository;

    public void isHallReady(ProjectionDTO projectionFront){
        List<Projection> projections = getAll();
        for(Projection projection: projections){
            if(projection.getDate().isEqual(projectionFront.getDate())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Sala je Zauzea u tom periodu!");
            }
        }
    }

    public void save(ProjectionDTO projection){
        isHallReady(projection);
        Optional<Movie> movie = movieRepository.findByName(projection.getMovie());
        if(!movie.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji film!");

        Optional<Hall> hall = hallRepository.findByMark(projection.getHall());
        if(!hall.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sala ne postoji!");

        Projection projection1 = new Projection();
        projection1.setDate(projection.getDate());
        projection1.setPrice(projection.getPrice());
        projection1.setNumOfReservedCards(projection.getNumOfReservedCards());
        projection1.setMovie(movie.get());
        projection1.setHall(hall.get());
        projectionRepository.save(projection1);

        movie.get().getProjections().add(projection1);
        movieRepository.save(movie.get());

        hall.get().getProjections().add(projection1);
        hallRepository.save(hall.get());
    }

    public List<Projection> getAll(){
        return projectionRepository.findAll();
    }

    public void delete(Long id){
        Projection projection = findById(id);
        projection.getHall().getProjections().remove(projection);
        projection.getMovie().getProjections().remove(projection);
        hallRepository.save(projection.getHall());
        movieRepository.save(projection.getMovie());
        projectionRepository.delete(projection);
    }

    public void update(ProjectionDTO projection){
        Optional<Movie> movie = movieRepository.findByName(projection.getMovie());
        if(!movie.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji film!");

        Optional<Hall> hall = hallRepository.findByMark(projection.getHall());
        if(!hall.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sala ne postoji!");

        Projection projection1 = findById(projection.getId());
        projection1.setDate(projection.getDate());
        projection1.setPrice(projection.getPrice());
        projection1.setNumOfReservedCards(projection.getNumOfReservedCards());
        projection1.setMovie(movie.get());
        projection1.setHall(hall.get());
        projectionRepository.save(projection1);

        movie.get().getProjections().add(projection1);
        movieRepository.save(movie.get());

        hall.get().getProjections().add(projection1);
        hallRepository.save(hall.get());
    }

    public List<Reservation> reservations(Long id){
        //TODO
        return null;
    }

    public List<Projection> findProjectionsByMovieId(Long id){
        Movie movie = movieService.findById(id);
        return movie.getProjections();
    }

    public Projection findById(Long id){
        Optional<Projection> projection = projectionRepository.findById(id);
        if(!projection.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ne postoji projekcija!");
        return projection.get();
    }

    public ProjectionDTO findByIdDto(Long id){
        Optional<Projection> projection = projectionRepository.findById(id);
        if(!projection.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ne postoji projekcija!");
        ProjectionDTO projectionDTO = new ProjectionDTO();
        projectionDTO.setId(projection.get().getId());
        projectionDTO.setNumOfReservedCards(projection.get().getNumOfReservedCards());
        projectionDTO.setPrice(projection.get().getPrice());
        projectionDTO.setMovie(projection.get().getMovie().getName());
        projectionDTO.setDate(projection.get().getDate());
        projectionDTO.setHall(projection.get().getHall().getMark());
        return projectionDTO;
    }
}
