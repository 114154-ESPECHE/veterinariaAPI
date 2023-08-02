package com.example.veterinariaapi.Controllers;

import com.example.veterinariaapi.Dtos.Login.LoginBody;
import com.example.veterinariaapi.Services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/usuario")
    public ResponseEntity<?> loginUsuario(@RequestBody @Valid LoginBody loginBody){
        try{
            loginService.loginUsuario(loginBody.getDni(), loginBody.getPassword());
            return ResponseEntity.ok("Welcome " + loginBody.getDni());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("User or Password invalid");
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<?> loginCliente(@RequestBody @Valid LoginBody loginBody){
        try{
            loginService.loginCliente(loginBody.getDni(), loginBody.getPassword());
            return ResponseEntity.ok("Welcome " + loginBody.getDni());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("User or Password invalid");
        }
    }


}
