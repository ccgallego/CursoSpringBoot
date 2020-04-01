/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.data.repository;

import co.edu.utp.isc.gia.restuser.data.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author utp
 */
public interface UserRepository extends CrudRepository<User, Long>{
    
}
