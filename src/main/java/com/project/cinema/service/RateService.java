package com.project.cinema.service;

import com.project.cinema.model.Movie;
import com.project.cinema.model.Rate;
import com.project.cinema.model.User;
import com.project.cinema.repository.MovieRepository;
import com.project.cinema.repository.RateRepository;
import com.project.cinema.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public void rate(Long movieId, Float value){
        if(value > 5 || value < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Losa vrednost za ocenu!");

        Movie movie = movieService.findById(movieId);
        User user = userService.getCurrentUser();
        Rate rate = new Rate();
        rate.setValue(value);
        rate.setUser(user);
        rate.setMovie(movie);
        rateRepository.save(rate);

        user.getRates().add(rate);
        userRepository.save(user);

        movie.getRates().add(rate);
        Double val = 0d;
        for(Rate r : movie.getRates()){
            val += r.getValue();
        }
        movie.setRating(val/movie.getRates().size());
        movieService.save(movie);
    }
}
