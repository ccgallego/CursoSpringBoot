/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.restuser.service.impl.UserServiceImpl;
import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author utp
 */
@RestController
@RequestMapping("user")
public class UserController {
    
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    
    @PostMapping()
    public UserDTO insert(@RequestBody UserDTO user){
        return userService.save(user);
    }
    
    @GetMapping()
    public List<UserDTO> getAll(){
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable("id") Long id){
        return userService.findOne(id);
    }
    
    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable("id") Long id){
        return userService.delete(id);
    }
    
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user){
      return userService.update(id, user);
    }
    
}
