/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.exceptions.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author utp
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NotContentException extends RuntimeException{
    public NotContentException(String exception) {
		super(exception);
	}
    
}
