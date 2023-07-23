package com.tapgroup.pwsalonreservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class PwsalonreservasApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwsalonreservasApplication.class, args);
        System.out.println("Levantado el Servidor");
    }

}
