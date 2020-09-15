package com.project.cinema.controller;

import com.project.cinema.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> rate(@RequestParam(value = "movie_id") Long movieId, @RequestParam(value = "value") Float value){
        rateService.rate(movieId, value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
