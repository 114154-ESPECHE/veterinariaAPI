package com.example.veterinariaapi.Controllers;

import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/getByDni/{dni}")
    public ResponseEntity<UsuarioRequestDTO> getUserDTOByDni(@PathVariable Long dni){
        UsuarioEntity usuario = usuarioService.getUsuarioEntityByDni(dni);
        if (Objects.isNull(usuario)){
            throw new RuntimeException("User not exist");
        }
        return ResponseEntity.ok(modelMapper.map(usuario, UsuarioRequestDTO.class));
    }

    //@PostMapping

    //@PutMapping

    //@DeleteMapping
}
