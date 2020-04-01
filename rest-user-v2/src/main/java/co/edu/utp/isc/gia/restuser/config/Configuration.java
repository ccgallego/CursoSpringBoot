/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;


/**
 *
 * @author utp
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }
}
