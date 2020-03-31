package co.edu.utp.isc.gia.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "hello")
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
        
        @GetMapping("/{nombre}/{apellido}")
        public String hello(@PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido){
            return "Hello " + nombre + " " + apellido;
        }
        
        @GetMapping()
        public String helloMundo(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido){
            return "Hello " + nombre + " " + apellido;
        }
        
        

}
