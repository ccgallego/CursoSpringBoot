/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author utp
 */
@RestController
@RequestMapping("calc")
public class CalculadoraController {

    //localhost:8080/calc/suma?num1=5.5&num2=5.5
    @GetMapping("/suma")
    public Double suma(@RequestParam("num1") double num1, @RequestParam("num2") double num2) {
        return num1 + num2;
    }

    
    //localhost:8080/calc/resta?num1=10&num2=5.5
    @GetMapping("/resta")
    public Double resta(@RequestParam("num1") double num1, @RequestParam("num2") double num2) {
        return num1 - num2;
    }
       
    
    //localhost:8080/calc/multiplicacion?num1=8&num2=10
    @GetMapping("/multiplicacion")
    public Double multiplicacion(@RequestParam("num1") double num1, @RequestParam("num2") double num2) {
        return num1 * num2;
    }
    
    //localhost:8080/calc/division?num1=5.5&num2=5.5
    @GetMapping("/division")
    public Double division(@RequestParam("num1") double num1, @RequestParam("num2") double num2) {
        if (num2 == 0.0) {
            System.out.println("No se puede dividir por cero");
            return 0.0;
        } else {
            return num1 / num2;
        }
    }
    
    

}
