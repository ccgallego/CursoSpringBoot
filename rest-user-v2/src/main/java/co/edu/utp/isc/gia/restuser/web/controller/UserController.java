/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.restuser.exceptions.ExResponseEntityExceptionHandler;
import co.edu.utp.isc.gia.restuser.exceptions.responses.BadRequestException;
import co.edu.utp.isc.gia.restuser.exceptions.responses.InternalServerErrorException;
import co.edu.utp.isc.gia.restuser.exceptions.responses.NoContentException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author utp
 */
@RestController
@RequestMapping("user")
public class UserController extends ExResponseEntityExceptionHandler {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> insert(@RequestBody UserDTO user) {
        //Valido
        if (user == null) {
            throw new BadRequestException("Datos de usuario invalidos");
            //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de usuario invalidos");     
        }
        //Ejecuto
        UserDTO u;
        try {
            u = this.userService.save(user);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error guardando al usuario, verificar los datos ingresados");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping() //localhost:8080/user/
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAll() throws Exception {
        List<UserDTO> list = userService.findAll();
        if (!list.isEmpty()) {
            return list;
        }
        throw new NoContentException("No hay datos disponibles");
    }

    @GetMapping("/{id}") //localhost:8080/user/getOne?id=8
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getOne(@PathVariable("id") Long id) {
        if (id == null) {
            throw new BadRequestException(("Debe ingresar el id del usuario a buscar"));
        }
        UserDTO user = userService.findOne(id);

        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO deleteUser(@PathVariable("id") Long id) {
        if (id == null) {
            throw new BadRequestException("Debe ingresar el id del usuario a eliminar");
        }
        UserDTO user = userService.delete(id);

        return user;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user) {
        if (id == null || user == null) {
            throw new BadRequestException("Todos los parametros son necesarios");
        }
        UserDTO u = this.userService.update(id, user);
        if (u != null) {
            return u;
        }
        throw new InternalServerErrorException("Error actualizando el usuario");
    }
}
