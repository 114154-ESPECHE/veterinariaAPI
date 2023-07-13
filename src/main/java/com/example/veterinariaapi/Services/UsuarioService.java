package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Models.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    UsuarioEntity getUsuarioEntityByDni(Long dni);
    UsuarioEntity getUsuarioByEmail(String email);
    UsuarioEntity saveUsuario(UsuarioEntity usuarioEntity);
}
