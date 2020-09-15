package com.project.cinema.controller;

import com.project.cinema.model.Movie;
import com.project.cinema.model.Projection;
import com.project.cinema.model.dto.ProjectionDTO;
import com.project.cinema.service.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projection")
public class ProjectionController {

    @Autowired
    private ProjectionService projectionService;

    @PreAuthorize("@authorizationCustom.hasAccess(\"MANAGER\")")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody ProjectionDTO projection){
        projectionService.save(projection);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"MANAGER\")")
    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id){
        projectionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"MANAGER\")")
    @RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody ProjectionDTO projection){
        System.out.println(projection);
        projectionService.update(projection);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projection> findById(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(projectionService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(value="/dto_value", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectionDTO> findByIdDTO(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(projectionService.findByIdDto(id),HttpStatus.OK);
    }

    @RequestMapping( value= "/getAll" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Projection>> getAll(){
        return new ResponseEntity<>(projectionService.getAll(),HttpStatus.OK);
    }

    @RequestMapping(value= "/findProjectionsByMovieId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Projection>> findProjectionsByMovieId(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(projectionService.findProjectionsByMovieId(id),HttpStatus.OK);
    }


    @RequestMapping( value = "/reservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reservations(@RequestParam(value = "id") Long id){
        projectionService.reservations(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
