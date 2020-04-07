/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.service.impl;

import co.edu.utp.isc.gia.restuser.data.entity.UserEntity;
import co.edu.utp.isc.gia.restuser.data.repository.UserRepository;
import co.edu.utp.isc.gia.restuser.exceptions.responses.BadRequestException;
import co.edu.utp.isc.gia.restuser.exceptions.responses.NotFoundException;
import co.edu.utp.isc.gia.restuser.service.UserService;
import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author utp
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO save(UserDTO user) {
        if (user == null) {
            throw new BadRequestException("Parametros no válidos");
        } else if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BadRequestException("Parametro username requerido");
        } else if (user.getName() == null || user.getName().isEmpty()) {
            throw new BadRequestException("Parametro name requerido");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException("Parametro password requerido");
        } else if (user.getEmail()== null || user.getEmail().isEmpty()){
            throw new BadRequestException("Parametro email requerido");
        } 
        
        user.setUsername(user.getUsername().toLowerCase());
        UserEntity u = this.userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(u, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll(){
        List<UserDTO> listaUsuarios = new ArrayList<>();
        Iterable<UserEntity> u = this.userRepository.findAll();
        while (u.iterator().hasNext()) {
            for (UserEntity user : u) {
                listaUsuarios.add(mapper.map(user, UserDTO.class));
            }
            return listaUsuarios;
        }
        return listaUsuarios;
    }

    @Override
    public UserDTO findOne(Long id){
        if(id == null){
            throw new BadRequestException("El id es necesario para consultar");
        }
        Optional<UserEntity> opUser = this.userRepository.findById(id);
        UserEntity u = opUser.orElseThrow(() -> new NotFoundException("No existe el usuario"));
       
        return mapper.map(u, UserDTO.class);
           
    }

    @Override
    public UserDTO delete(Long id){
        if(id == null){
            throw new BadRequestException("El id es necesario para consultar");
        }
        Optional<UserEntity> opUser = this.userRepository.findById(id);
        UserEntity u = opUser.orElseThrow(() -> new NotFoundException("No existe el usuario"));
        this.userRepository.deleteById(id);
        
        return mapper.map(u, UserDTO.class);
        
    }

    @Override
    public UserDTO update(Long id, UserDTO user) {
        if(id == null || user == null){
            throw new BadRequestException("Todos los parametros son necesarios");
        } else if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BadRequestException("Parametro username requerido");
        } else if (user.getName() == null || user.getName().isEmpty()) {
            throw new BadRequestException("Parametro name requerido");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException("Parametro password requerido");
        } else if (user.getEmail()== null || user.getEmail().isEmpty()){
            throw new BadRequestException("Parametro email requerido");
        }
        
        Optional<UserEntity> opUser = this.userRepository.findById(id);
        UserEntity u = opUser.orElseThrow(() -> new NotFoundException("No existe el usuario"));
        
        user.setId(u.getId());
        user.setUsername(user.getUsername().toLowerCase());
        UserEntity us = this.userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(us, UserDTO.class);
        
    }

}
