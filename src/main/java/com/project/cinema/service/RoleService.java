package com.project.cinema.service;

import com.project.cinema.model.Role;
import com.project.cinema.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id){
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Role with id: " + id + " does not exist!");
        return role.get();
    }

    public void update(Role role){
        Role roleOrig = findById(role.getId());
        roleOrig.setName(role.getName());
        save(role);
    }

    public void save(Role role){
        roleRepository.save(role);
    }

    public void delete(Role role){ roleRepository.delete(role); }

    public void delete(Long id){
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Role with id: " + id + " does not exist!");
        delete(role.get());
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
