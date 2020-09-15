package com.project.cinema.controller;

import com.project.cinema.model.Hall;
import com.project.cinema.model.Projection;
import com.project.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hall")
public class HallController {

    @Autowired
    private HallService hallService;

    @PreAuthorize("@authorizationCustom.hasAccess(\"MANAGER\")")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Hall hall, @RequestParam Long cinemaId){
        hallService.save(hall, cinemaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"MANAGER\")")
    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id){
        hallService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"MANAGER\")")
    @RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Hall hall){
        hallService.update(hall);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hall> findById(@RequestParam(value = "id") Long id){

        return new ResponseEntity<>(hallService.findById(id), HttpStatus.OK);
    }

    @RequestMapping( value = "/projections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Projection>> projections(@RequestParam(value = "id") Long id, @RequestParam(value = "from") Date from, @RequestParam(value = "to") Date to){

        return new ResponseEntity<>(hallService.projections(id,from,to), HttpStatus.OK);
    }
}
