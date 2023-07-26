package com.example.veterinariaapi.Controllers;

import com.example.veterinariaapi.Dtos.Usuario.NewUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UpdateUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/getByDni/{dni}")
    public ResponseEntity<UsuarioRequestDTO> getUserDTOByDni(@PathVariable Long dni){
        UsuarioEntity usuario = usuarioService.getUsuarioByDni(dni);
        if (Objects.isNull(usuario)){
            throw new RuntimeException("User not exist");
        }
        return ResponseEntity.ok(modelMapper.map(usuario, UsuarioRequestDTO.class));
    }

    @PostMapping("/saveUsuario")
    public ResponseEntity<UsuarioRequestDTO> createUsuario(@RequestBody NewUsuarioRequestDTO newUsuario){
        UsuarioRequestDTO usuario = usuarioService.createUsuario(newUsuario);
        if (Objects.isNull(usuario.getDni())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }else{
            return ResponseEntity.ok(usuario);
        }

    }

    //@PutMapping

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long dni){
        UsuarioEntity usuario = usuarioService.getUsuarioByDni(dni);
        usuarioService.deleteUsuario(dni);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{dni}")
    public ResponseEntity<UsuarioRequestDTO> updateUsuario(@PathVariable Long dni, @RequestBody UpdateUsuarioRequestDTO updatedUser){
        UsuarioEntity usuario = usuarioService.getUsuarioByDni(dni);
        UsuarioRequestDTO userUpdate =  usuarioService.updateUsuario(dni, updatedUser);
        return ResponseEntity.ok(userUpdate);
    }
}
