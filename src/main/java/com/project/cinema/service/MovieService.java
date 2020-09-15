package com.project.cinema.service;

import com.project.cinema.model.*;
import com.project.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private ProjectionService projectionService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;

    public void save(Movie movie){
        movieRepository.save(movie);
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public List<Movie> watched(){
        User user = userService.getCurrentUser();
        return user.getWatchedFilms();
    }

    public List<Movie> watchedRated(){
        User user = userService.getCurrentUser();
        List<Movie> rated = new ArrayList<>();
        for(Movie m : user.getWatchedFilms()){
            if(isRated(m,user))
                rated.add(m);
        }

        return rated;
    }

    public List<Movie> watchedNoRated(){
        User user = userService.getCurrentUser();
        List<Movie> noRated = new ArrayList<>();
        for(Movie m : user.getWatchedFilms()){
            if(!isRated(m,user))
                noRated.add(m);
        }

        return noRated;
    }

    private Boolean isRated(Movie movie, User user){
        for(Rate r: user.getRates()){
            if(r.getMovie().getId() == movie.getId())
                return true;
        }
        return false;
    }

    public void delete(Long id){
        //TODO
    }

    public void update(Movie movie){
        //TODO
    }

    public Movie findById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        if(!movie.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
            , "Movie with id:" + id + " does not exist!");
        }
        return movie.get();
    }

    public List<Projection> projections(Long id){
        //TODO
        return null;
    }

    public Genre setGenre(String genre){
        if(genre == "ACTION"){
            return Genre.ACTION;
        }else if(genre == "COMEDY"){
            return Genre.COMEDY;
        }
        return null;
    }

    public List<Movie> search(String name,String description,String genre,Double rating,LocalDateTime startDate,LocalDateTime endDate,Double price){
        List<Movie> res = new ArrayList<>();
        List<Projection> projections = projectionService.getAll();
        Genre newGenre = setGenre(genre);


        for(Projection itProjection: projections){
            if(itProjection.getMovie() == null){
                continue;
            }
            if(!itProjection.getMovie().getName().toLowerCase().contains(name.toLowerCase())){
                continue;
            }
            if(!itProjection.getMovie().getDescription().toLowerCase().contains(description.toLowerCase())){
                continue;
            }
            if(newGenre != null && itProjection.getMovie().getGenre() != newGenre){
                continue;
            }
            if(rating != null && itProjection.getMovie().getRating() < rating){
                continue;
            }
            if(price != null && itProjection.getPrice() < price){
                continue;
            }
            if(startDate != null){
                if(itProjection.getDate().isBefore(startDate)) {
                    continue;
                }
            }
            if(endDate != null){
                if(itProjection.getDate().isBefore(startDate)) {
                    continue;
                }
            }
            res.add(itProjection.getMovie());
        }
        return res;
    }

    public List<Movie> all(){
        return movieRepository.findAll();
    }
}
