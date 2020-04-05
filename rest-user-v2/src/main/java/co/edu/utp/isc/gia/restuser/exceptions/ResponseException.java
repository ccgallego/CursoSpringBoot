/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.exceptions;

import java.util.Date;

/**
 *
 * @author utp
 */

public class ResponseException {
    private Date timestamp;
    private String message;
    private String detalles;

    public ResponseException(Date timestamp, String message, String detalles) {
        this.timestamp = timestamp;
        this.message = message;
        this.detalles = detalles;
    }
    

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    
    
}
