/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.service.impl;

import co.edu.utp.isc.gia.restuser.service.UserService;
import co.edu.utp.isc.gia.restuser.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author utp
 */
@Service
public class UserServiceImpl implements UserService {

    private List<UserDTO> users = new ArrayList<>();

    @Override
    public UserDTO save(UserDTO user) {
        for (int i = 0; i < users.size(); i++) {
            user.setId(users.size() + 1L);
            if (user.getId() == users.get(i).getId()) {
                user.setId(user.getId() + 1L);
                user.setUsername(user.getUsername().toLowerCase());
                users.add(user);
                return user;
            }
        }
        user.setId(users.size() + 1L);
        user.setUsername(user.getUsername().toLowerCase());
        users.add(user);
        return user;

    }

    @Override
    public List<UserDTO> findAll() {
        return users;
    }

    @Override
    public UserDTO findOne(Long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get((i)).getId() == id) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public UserDTO delete(Long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get((i)).getId() == id) {
                UserDTO u = users.get(i);
                users.remove(i);
                return u;
            }
        }
        return null;
    }

    @Override
    public UserDTO update(Long id, UserDTO user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get((i)).getId() == id) {
                Long id2 = users.get(i).getId();
                user.setId(id2);
                users.set(i, user);
                return user;
            }
        }
        return null;
    }

}
