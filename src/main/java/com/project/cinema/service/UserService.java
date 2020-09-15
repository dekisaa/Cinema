package com.project.cinema.service;

import com.project.cinema.model.*;
import com.project.cinema.model.dto.UserDTO;
import com.project.cinema.repository.RoleRepository;
import com.project.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public Role currentRole(){
        User user = getCurrentUser();
        Role role = user.getRoles().iterator().next();
        return  role;
    }

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void enable(Boolean value, Long id){
        Optional<User> manager = userRepository.findById(id);
        if(!manager.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ne postoji manager!");
        manager.get().setActive(value);
        userRepository.save(manager.get());
    }

    public void newManager(UserDTO userDTO){
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is registered!");
        }
        Role role = roleRepository.findByName("MANAGER")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        Set<Role> tempRoles = new HashSet<>();

        String password = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPhone(userDTO.getPhone());
        user.setBirthDay(userDTO.getBirthDay());
        tempRoles.add(role);
        user.setRoles(tempRoles);
        user.setPassword(password);

        user.setActive(true);


        user = userRepository.save(user);
    }

    public User signUp(UserDTO userDTO){
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is registered!");
        }
        System.out.println(userDTO.getRole().trim());
        Role role = roleRepository.findByName(userDTO.getRole().trim())
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        Set<Role> tempRoles = new HashSet<>();

        String password = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPhone(userDTO.getPhone());
        user.setBirthDay(userDTO.getBirthDay());
        tempRoles.add(role);
        user.setRoles(tempRoles);
        user.setPassword(password);

        if(userDTO.getRole().equals("VIEWER")){
            user.setActive(true);
        }else{
            user.setActive(false);
        }

        user = userRepository.save(user);


        userDTO.setPassword("");

        return user;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get();
    }

    public List<UserDTO> managers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> managers = new ArrayList<>();
        Optional<Role> role = roleRepository.findById(2l);
        for(User u : users){
            if(u.getRoles().contains(role.get()))
                managers.add(new UserDTO(u));
        }
        return managers;
    }

    public void update(User user){
        userRepository.save(user);
    }

    public List<Movie> watchedMovies(){
        //TODO
        return null;
    }

    public List<Movie> watchedMovies(Boolean rated){
        //TODO
        return null;
    }

    public List<Movie> reservedMovies(){
        //TODO
        return null;
    }

    public List<Rate> rates(){
        //TODO
        return null;
    }

    public void enableUser(Long id){
        //TODO
    }

    public List<Cinema> myCinemas(){
        //TODO
        return null;
    }

    public void delete(Long id){
        //TODO
    }

    public void changePass(String pass1, String pass2){
        if(!pass1.equals(pass2))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Razlicite sifre!");
        User user = getCurrentUser();
        String encoded = passwordEncoder.encode(pass1);
        user.setPassword(encoded);
        userRepository.save(user);
    }

}
