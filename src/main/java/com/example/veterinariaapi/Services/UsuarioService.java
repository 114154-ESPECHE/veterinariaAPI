package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Dtos.Usuario.NewUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UpdateUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Models.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    UsuarioEntity getUsuarioByDni(Long dni);
    UsuarioEntity getUsuarioByEmail(String email);
    UsuarioRequestDTO createUsuario(NewUsuarioRequestDTO newUsuarioRequestDTO);
    void deleteUsuario(Long dni);
    UsuarioRequestDTO updateUsuario(Long dni, UpdateUsuarioRequestDTO updateUser);

    UsuarioRequestDTO getUsuarioByDniAndPassword(Long dni, String password);
}
