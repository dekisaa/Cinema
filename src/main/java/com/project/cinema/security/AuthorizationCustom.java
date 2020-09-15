package com.project.cinema.security;

import com.project.cinema.model.Role;
import com.project.cinema.model.User;
import com.project.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class AuthorizationCustom {

    @Autowired
    private UserService userService;

    public Boolean hasAccess(String role){
        User user = userService.getCurrentUser();
        Set<Role> set = user.getRoles();

        for (Role r : set) {
            if(r.getName().equals(role))
                return true;
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have a permission!");
    }
}
