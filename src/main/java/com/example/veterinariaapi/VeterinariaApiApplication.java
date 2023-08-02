package com.example.veterinariaapi;

import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Services.ClienteService;
import com.example.veterinariaapi.Services.Impl.ClienteServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeterinariaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinariaApiApplication.class, args);

    }

}
