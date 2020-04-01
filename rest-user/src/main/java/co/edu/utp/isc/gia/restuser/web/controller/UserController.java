/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author utp
 */
@RestController
@RequestMapping("user")
public class UserController {
    
    private List<UserDTO> users = new ArrayList<>();
    
    @PostMapping()
    public UserDTO save(UserDTO user){
        user.setUsername(user.getUsername().toLowerCase());
        users.add(user);
        return user;
    }
    
}
