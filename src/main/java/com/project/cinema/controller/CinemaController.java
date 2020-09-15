package com.project.cinema.controller;

import com.project.cinema.model.Cinema;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Projection;
import com.project.cinema.model.dto.CinemaDTO;
import com.project.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PreAuthorize("@authorizationCustom.hasAccess(\"ADMIN\")")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CinemaDTO cinema){
        cinemaService.create(cinema);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"ADMIN\")")
    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id){
        cinemaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"ADMIN\")")
    @RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody CinemaDTO cinema){
        cinemaService.update(cinema);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cinema> findById(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(cinemaService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/dto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaDTO> cinemaDto(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(cinemaService.cinemaDto(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cinema>> all(){
        return new ResponseEntity<>(cinemaService.all(),HttpStatus.OK);
    }

    @RequestMapping(value = "/halls",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hall>> halls(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(cinemaService.halls(id), HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"MANAGER\")")
    @RequestMapping(value = "/manager_cinemas",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cinema>> managerCinemas(){
        return new ResponseEntity<>(cinemaService.maganerCinemas(), HttpStatus.OK);
    }

    @RequestMapping(value = "/all_projections",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Projection>> allProjections(@RequestParam Long id){
        return new ResponseEntity<>(cinemaService.allProjections(id), HttpStatus.OK);
    }
}
