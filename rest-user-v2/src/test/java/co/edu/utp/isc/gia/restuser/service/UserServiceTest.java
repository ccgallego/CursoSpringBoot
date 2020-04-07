/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.service;

import co.edu.utp.isc.gia.restuser.data.entity.UserEntity;
import co.edu.utp.isc.gia.restuser.data.repository.UserRepository;
import co.edu.utp.isc.gia.restuser.exceptions.responses.BadRequestException;
import co.edu.utp.isc.gia.restuser.exceptions.responses.InternalServerErrorException;
import co.edu.utp.isc.gia.restuser.exceptions.responses.NotFoundException;
import co.edu.utp.isc.gia.restuser.service.impl.UserServiceImpl;
import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
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
    public void testSaveNull(){
        userDto = null;
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        assertThrows(BadRequestException.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
    }
    
    
    @Test()
    public void testSaveUsernameEmpty() throws Exception{
        userDto = new UserDTO(null, null, "1223", "Cristian Gallego", "cgallego@gmail.com");
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        Exception eThrows = assertThrows(BadRequestException.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        assertTrue(eThrows.getMessage().contains("Parametro username requerido"));
    }
    
    @Test()
    public void testSavePasswordEmpty() throws Exception{
        userDto = new UserDTO(null, "cgallego", null, "Cristian Gallego", "cgallego@gmail.com");
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        Exception eThrows = assertThrows(BadRequestException.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        assertTrue(eThrows.getMessage().contains("Parametro password requerido"));
    }
    
    @Test()
    public void testSaveNameEmpty() throws Exception{
        userDto = new UserDTO(null, "cgallego", "1223", null, "cgallego@gmail.com");
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        
        assertThrows(BadRequestException.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        //assertTrue(eThrows.getMessage().contains("Parametro name requerido"));
    }
    
    @Test()
    public void testSaveEmailEmpty() throws Exception{
        userDto = new UserDTO(null, "cgallego", "1223", "Cristian Gallego", null);
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
          
        Exception eThrows = assertThrows(BadRequestException.class, () -> {
            UserDTO result = instance.save(userDto);
        });   
        
        assertTrue(eThrows.getMessage().contains("Parametro email requerido"));
    }
    
    @Test
    public void testFindAllUsers(){
        List<UserEntity> usuarios = new ArrayList<>();
        usuarios.add(new UserEntity(1L, "cgallego", "123", "Cristian Gallego", "cgallego202@gmail.com"));
        when(userRepository.findAll()).thenReturn(usuarios);
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        
        List<UserDTO> expected = new ArrayList<>();
        expected.add(new UserDTO(1L, "cgallego", "123", "Cristian Gallego", "cgallego202@gmail.com"));
        
        List<UserDTO> result = instance.findAll();
        
        assertEquals(expected.get(0).getId(), result.get(0).getId());         
        assertEquals(expected.get(0).getUsername(), result.get(0).getUsername());         
        assertEquals(expected.get(0).getPassword(), result.get(0).getPassword());         
        assertEquals(expected.get(0).getName(), result.get(0).getName());         
        assertEquals(expected.get(0).getEmail(), result.get(0).getEmail());         
    }
    
    @Test
    public void testFindUsersNull(){    
        List<UserEntity> usuarios = new ArrayList();
        when(userRepository.findAll()).thenReturn(usuarios);
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        
        List<UserDTO> result = instance.findAll();
        
        assertTrue(result.isEmpty());
    }
    
    
    @Test
    public void testFindOneOk() throws Exception{    
        userEntity = new UserEntity(1L, "cgallego", "123", "Cristian Gallego", "cgallego202@gmail.com");
    
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        
        UserDTO expect = new UserDTO(1L, "cgallego", "123", "Cristian Gallego", "cgallego202@gmail.com");
        
        UserDTO result = instance.findOne(1L);
        
        assertEquals(expect, result);
       
    }
    
    @Test
    public void testFindOneIdNull(){       
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);     
        Long id = null;       
        assertThrows(BadRequestException.class, () -> {
            UserDTO result = instance.findOne(id);
        });
             
    }
    
    @Test
    public void testFindOneIsEmpty() {       
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        
        Long id = 39L; 
        
        assertThrows(NotFoundException.class, () -> {
            UserDTO u = instance.findOne(id);
        });
        
    }
    
    @Test
    public void testDeleteOk(){
        userEntity = new UserEntity(1L, "cgallego", "123", "Cristian Gallego", "cgallego202@gmail.com");
        
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userEntity));
        UserDTO expect = new UserDTO(1L, "cgallego", "123", "Cristian Gallego", "cgallego202@gmail.com");
         
        UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);
        
        Long id = 1L;

        doNothing().when(userRepository).deleteById(id);
        
        UserDTO result = instance.delete(id);
        
        assertEquals(expect, result);
    }
    
    @Test
    public void testDeleteIdNull(){
      UserServiceImpl instance = new UserServiceImpl(userRepository, mapper);     
        Long id = null;       
        assertThrows(BadRequestException.class, () -> {
            UserDTO result = instance.delete(id);
        });
    }
    
    
    
    
}
