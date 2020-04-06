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
    
    UserDTO save(UserDTO user) throws Exception;
    List<UserDTO> findAll() throws Exception;
    UserDTO findOne(Long id) throws Exception;
    UserDTO delete(Long id) throws Exception;;
    
}
