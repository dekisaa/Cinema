package com.project.cinema.controller;

import com.project.cinema.model.Cinema;
import com.project.cinema.model.Role;
import com.project.cinema.model.User;
import com.project.cinema.model.dto.UserDTO;
import com.project.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody User user){
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping( value = "/current_user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getCurrentUser(){
        UserDTO userDTO = new UserDTO();
        return new ResponseEntity<>(userDTO = new UserDTO(userService.getCurrentUser()),HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"ADMIN\")")
    @RequestMapping( value = "/new_manager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newManager(@RequestBody UserDTO user){
        userService.newManager(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( value = "/change_pass", method = RequestMethod.POST)
    public ResponseEntity<?> changePass(@RequestParam String pass1, @RequestParam String pass2){
        userService.changePass(pass1,pass2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"ADMIN\")")
    @RequestMapping( value = "/enable", method = RequestMethod.POST)
    public ResponseEntity<?> enable(@RequestParam Boolean value, @RequestParam Long id){
        userService.enable(value,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( value = "/managers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> managers(){
        return new ResponseEntity<>(userService.managers(),HttpStatus.OK);
    }

    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping( value = "/watched_movies", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> watchedMovies(){
        return new ResponseEntity<>(userService.watchedMovies(),HttpStatus.OK);
    }

    @RequestMapping( value = "/watched_movies_r", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> watchedMovies(@RequestParam(value = "rated") Boolean rated){
        return new ResponseEntity<>(userService.watchedMovies(rated),HttpStatus.OK);
    }
    @PreAuthorize("@authorizationCustom.hasAccess(\"VIEWER\")")
    @RequestMapping( value = "/reserved_movies", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reservedMovies(){
        return new ResponseEntity<>(userService.reservedMovies(),HttpStatus.OK);
    }

    @RequestMapping( value = "/rates", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rates(){
        return new ResponseEntity<>(userService.rates(),HttpStatus.OK);
    }

    @RequestMapping( value = "/my_cinemas", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cinema>> myCinemas(){
        return new ResponseEntity<>(userService.myCinemas(),HttpStatus.OK);
    }

    @RequestMapping( value = "/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getCurrentRole(){
        return new ResponseEntity<>(userService.currentRole(),HttpStatus.OK);
    }
}
