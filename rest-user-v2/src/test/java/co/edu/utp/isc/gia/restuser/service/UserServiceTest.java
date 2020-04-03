/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.service;

import co.edu.utp.isc.gia.restuser.data.entity.UserEntity;
import co.edu.utp.isc.gia.restuser.data.repository.UserRepository;
import co.edu.utp.isc.gia.restuser.service.impl.UserServiceImpl;
import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;

/**
 *
 * @author utp
 */

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private ModelMapper mapper;
    
    UserEntity userEntity;
    UserDTO userDto;
    
    public UserServiceTest() {
    }
    
    @BeforeEach
    public void setUp(){
        userRepository = Mockito.mock(UserRepository.class);
        mapper = new ModelMapper();
        userEntity = new UserEntity();
        userDto = new UserDTO();    
    }
    
    @Test
    public void testAllDataOkUser() throws Exception{
        userEntity = new UserEntity(1L, "cgallego", "1223", "Cristian Gallego", "cgallego@gmail.com");
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        
        //input
        userDto = new UserDTO(null, "CGALLEGO", "1223", "Cristian Gallego", "cgallego@gmail.com");
        
        //target
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        
        //expect
        UserDTO expect = new UserDTO(1L, "cgallego", "1223", "Cristian Gallego", "cgallego@gmail.com");
        
        //Test
        UserDTO result = instance.save(userDto);
        
        assertEquals(expect.getId(), result.getId());
        assertEquals(expect.getUsername(), result.getUsername());
        assertEquals(expect.getName(), result.getName());
        assertEquals(expect.getPassword(), result.getPassword());
        assertEquals(expect.getEmail(), result.getEmail());
    }
    
    @Test()
    public void testSaveUsernameEmpty() throws Exception{
        userDto = new UserDTO(null, null, "1223", "Cristian Gallego", "cgallego@gmail.com");
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        Exception eThrows = assertThrows(Exception.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        assertTrue(eThrows.getMessage().contains("Parametro username requerido"));
    }
    
    @Test()
    public void testSavePasswordEmpty() throws Exception{
        userDto = new UserDTO(null, "cgallego", null, "Cristian Gallego", "cgallego@gmail.com");
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        Exception eThrows = assertThrows(Exception.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        assertTrue(eThrows.getMessage().contains("Parametro password requerido"));
    }
    
    @Test()
    public void testSaveNameEmpty() throws Exception{
        userDto = new UserDTO(null, "cgallego", "1223", null, "cgallego@gmail.com");
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        Exception eThrows = assertThrows(Exception.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        assertTrue(eThrows.getMessage().contains("Parametro name requerido"));
    }
    
    @Test()
    public void testSaveEmailEmpty() throws Exception{
        userDto = new UserDTO(null, "cgallego", "1223", "Cristian Gallego", null);
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        Exception eThrows = assertThrows(Exception.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        assertTrue(eThrows.getMessage().contains("Parametro email requerido"));
    }
    
    
    
    
    
    /*@Test
    public void saveUserNull() throws Exception{
        userEntity = new UserEntity(null, null, null, null,null);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        
        userDto = new UserDTO(null, null, null, null,null);
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        UserDTO expect = new UserDTO(null, null, null, null,null);
        
        UserDTO result = instance.save(userDto);
        
        assertEquals(expect, result);
    }*/
    
}
