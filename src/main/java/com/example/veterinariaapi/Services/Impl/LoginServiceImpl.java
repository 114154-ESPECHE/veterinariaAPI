package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Models.Usuario;
import com.example.veterinariaapi.Services.LoginService;
import com.example.veterinariaapi.Services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Usuario login(Long dni, String password) {
        UsuarioRequestDTO usuarioRequestDTO = usuarioService.getUsuarioByDniAndPassword(dni, password);

        if (usuarioRequestDTO == null){
            throw new EntityNotFoundException("User not exist");
        }
        return modelMapper.map(usuarioRequestDTO, Usuario.class);
    }
}
