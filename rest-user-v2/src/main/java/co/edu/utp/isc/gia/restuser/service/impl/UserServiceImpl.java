/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.service.impl;

import co.edu.utp.isc.gia.restuser.data.entity.User;
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

    private List<UserDTO> users = new ArrayList<>();

    private UserRepository userRepository;

    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO save(UserDTO user) {
        user.setUsername(user.getUsername().toLowerCase());
        User u = this.userRepository.save(mapper.map(user, User.class));
        return mapper.map(u, UserDTO.class);
    }
    
    @Override
    public List<UserDTO> findAll() {
        Iterable<User> u = this.userRepository.findAll();
        List<UserDTO> listaUsuarios = new ArrayList<>();
        if(u != null){
            for (User user : u ) {
                listaUsuarios.add(mapper.map(user, UserDTO.class));
            }
            return listaUsuarios;
        }
        return null;   
    }

    @Override
    public UserDTO findOne(Long id) {
        Optional<User> opUser = this.userRepository.findById(id);
        if(opUser.isPresent()){
            return mapper.map(opUser.get(), UserDTO.class);
        }
        return null;
    }

    @Override
    public UserDTO delete(Long id) {
        Optional<User> opUser = this.userRepository.findById(id);
        if(opUser.isPresent()){
            this.userRepository.deleteById(id);
            return mapper.map(opUser.get(), UserDTO.class);
        }
        return null;
    }

}
