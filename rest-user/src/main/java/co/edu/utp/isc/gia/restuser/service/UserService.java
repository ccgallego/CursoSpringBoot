/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.service;

import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import java.util.List;

/**
 *
 * @author utp
 */
public interface UserService {
    
    UserDTO save(UserDTO user);
    List<UserDTO> findAll();
    UserDTO findOne(Long id);
    UserDTO delete(Long id);
    UserDTO update(Long id, UserDTO user);
    
}
