package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Models.Usuario;
import com.example.veterinariaapi.Repositories.jpa.UsuarioJpaRepository;
import com.example.veterinariaapi.Services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UsuarioJpaRepository usuarioJpaRepository;
    @Override
    public UsuarioEntity getUsuarioEntityByDni(Long dni) {
        UsuarioEntity usuarioEntity = usuarioJpaRepository.getReferenceById(dni);
        if (Objects.isNull(usuarioEntity.getDni())){
            throw new RuntimeException("Usuario no encontrado");
        }
        return modelMapper.map(usuarioEntity, UsuarioEntity.class);
    }

    @Override
    public UsuarioEntity getUsuarioByEmail(String email) {
        UsuarioEntity usuarioEntity = usuarioJpaRepository.getUsuarioEntitiesByEmail(email);
        if (Objects.isNull(usuarioEntity.getEmail())){
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuarioEntity;
    }
}
