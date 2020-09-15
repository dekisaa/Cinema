package com.project.cinema.controller;

import com.project.cinema.model.Movie;
import com.project.cinema.model.Reservation;
import com.project.cinema.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestParam(value = "projection_id") Long projectionId, @RequestParam(value = "num_of_cards") Integer numOfCards){
        reservationService.create(projectionId, numOfCards);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( value= "/getAll" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> getAll(){
        return new ResponseEntity<>(reservationService.getAll(),HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> confirm(@RequestParam(value = "id") Long id){
        reservationService.confirm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<?> cancel(@RequestParam(value = "reservation_id") Long reservationId){
        reservationService.cancel(reservationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
