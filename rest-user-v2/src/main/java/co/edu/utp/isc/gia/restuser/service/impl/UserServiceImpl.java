/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.service.impl;

import co.edu.utp.isc.gia.restuser.data.entity.UserEntity;
import co.edu.utp.isc.gia.restuser.data.repository.UserRepository;
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
    public UserDTO save(UserDTO user) throws Exception {
        if (user == null) {
            throw new Exception("Parametros no válidos");
        } else if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new Exception("Parametro username requerido");
        } else if (user.getName() == null || user.getName().isEmpty()) {
            throw new Exception("Parametro name requerido");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new Exception("Parametro password requerido");
        } else if (user.getEmail()== null || user.getEmail().isEmpty()){
            throw new Exception("Parametro email requerido");
        } 
        
        user.setUsername(user.getUsername().toLowerCase());
        UserEntity u = this.userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(u, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        Iterable<UserEntity> u = this.userRepository.findAll();
        List<UserDTO> listaUsuarios = new ArrayList<>();
        if (u != null) {
            for (UserEntity user : u) {
                listaUsuarios.add(mapper.map(user, UserDTO.class));
            }
            return listaUsuarios;
        }
        return null;
    }

    @Override
    public UserDTO findOne(Long id) {
        Optional<UserEntity> opUser = this.userRepository.findById(id);
        if (opUser.isPresent()) {
            return mapper.map(opUser.get(), UserDTO.class);
        }
        return null;
    }

    @Override
    public UserDTO delete(Long id) {
        Optional<UserEntity> opUser = this.userRepository.findById(id);
        if (opUser.isPresent()) {
            this.userRepository.deleteById(id);
            return mapper.map(opUser.get(), UserDTO.class);
        }
        return null;
    }

}
