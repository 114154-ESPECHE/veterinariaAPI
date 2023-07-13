package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Usuario.NewUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UpdateUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Models.Usuario;
import com.example.veterinariaapi.Repositories.jpa.UsuarioJpaRepository;
import com.example.veterinariaapi.Services.UsuarioService;
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

    @Override
    public UsuarioRequestDTO saveUsuario(NewUsuarioRequestDTO newUsuarioRequestDTO) {
        UsuarioEntity user = usuarioJpaRepository.getUsuarioEntitiesByDni(newUsuarioRequestDTO.getDni());
        if (Objects.isNull(user)) {
            throw new RuntimeException("The User exist");
        }
        UsuarioEntity newUsuario = modelMapper.map(newUsuarioRequestDTO, UsuarioEntity.class);
        usuarioJpaRepository.save(newUsuario);
        return modelMapper.map(newUsuario, UsuarioRequestDTO.class);
    }

    @Override
    public void deleteUsuario(Long dni) {
        UsuarioEntity usuario = usuarioJpaRepository.getUsuarioEntitiesByDni(dni);
        if (Objects.isNull(usuario)){
            throw new RuntimeException("The User exist");
        }
        usuarioJpaRepository.delete(usuario);
    }

    @Override
    public UpdateUsuarioRequestDTO updateUsuario(Long dni, UsuarioEntity usuarioEntity) {
        UsuarioEntity usuario = usuarioJpaRepository.getUsuarioEntitiesByDni(dni);
        if (Objects.isNull(usuario.getDni())){
            throw new RuntimeException("The User exist");
        }
        usuario.setPassword(usuarioEntity.getPassword());
        usuario.setFechaNacimiento(usuarioEntity.getFechaNacimiento());
        usuario.setTelefono(usuarioEntity.getTelefono());
        usuario.setDireccion(usuarioEntity.getDireccion());
        usuario.setEmail(usuarioEntity.getEmail());

        return modelMapper.map(usuario, UpdateUsuarioRequestDTO.class);
    }


}
