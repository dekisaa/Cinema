package com.project.cinema.controller;

import com.project.cinema.model.Movie;
import com.project.cinema.model.Projection;
import com.project.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Movie movie){
        movieService.save(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id){
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Movie movie){
        movieService.update(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> findById(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(movieService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/watched", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> watched(){
        return new ResponseEntity<>(movieService.watched(),HttpStatus.OK);
    }

    @RequestMapping(value = "/watched_rated", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> watchedRated(){
        return new ResponseEntity<>(movieService.watchedRated(),HttpStatus.OK);
    }

    @RequestMapping(value = "/watched_no_rated", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> watchedNoRated(){
        return new ResponseEntity<>(movieService.watchedNoRated(),HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> all(){
        return new ResponseEntity<>(movieService.all(),HttpStatus.OK);
    }

    @RequestMapping( value= "/getAll" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAll(){
        return new ResponseEntity<>(movieService.getAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/projections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Projection>> projections(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(movieService.projections(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> search(@RequestParam(value = "name", required=false, defaultValue = "") String name,
                                              @RequestParam(value = "description", required=false, defaultValue = "") String description,
                                              @RequestParam(value = "genre", required=false, defaultValue = "") String genre,
                                              @RequestParam(value = "rating", required=false) Double rating,
                                              @RequestParam(value = "startDate", required=false) String startDate,
                                              @RequestParam(value = "endDate", required=false) String endDate,
                                              @RequestParam(value = "price", required=false) Double price) {


        LocalDateTime dateTime1 = null;
        LocalDateTime dateTime2 = null;
        if(!startDate.isEmpty()) {
            dateTime1 = LocalDateTime.parse(startDate);
        }
        if(!endDate.isEmpty()){
            dateTime2 = LocalDateTime.parse(endDate);
        }
        return new ResponseEntity<>(movieService.search(name, description, genre, rating, dateTime1, dateTime2, price), HttpStatus.OK);
    }

}
