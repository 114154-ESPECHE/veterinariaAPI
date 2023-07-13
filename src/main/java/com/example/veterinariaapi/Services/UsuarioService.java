package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Dtos.Usuario.NewUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Models.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    UsuarioEntity getUsuarioEntityByDni(Long dni);
    UsuarioEntity getUsuarioByEmail(String email);
    UsuarioRequestDTO saveUsuario(NewUsuarioRequestDTO newUsuarioRequestDTO);
    void deleteUsuario(Long dni);
    UsuarioEntity updateUsuario(Long dni);
}
