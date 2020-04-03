/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.restuser.service.impl.UserServiceImpl;
import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> insert(@RequestBody UserDTO user){
        //Valido
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de usuario invalidos");     
        }
        
        //Ejecuto
        UserDTO u;
        try {
            u = this.userService.save(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> list = userService.findAll();
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") Long id) {
        UserDTO user = userService.findOne(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id) {
        UserDTO user = userService.delete(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user) {
        UserDTO u = this.userService.findOne(id);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId(u.getId());
        u = this.userService.save(user);
        if (u != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }*/
    
    
}
