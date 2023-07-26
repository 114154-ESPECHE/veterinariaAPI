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

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UsuarioJpaRepository usuarioJpaRepository;
    @Override
    public UsuarioEntity getUsuarioByDni(Long dni) {
        Optional <UsuarioEntity> usuarioEntity = usuarioJpaRepository.getUsuarioEntitiesByDni(dni);
        if (usuarioEntity.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        return modelMapper.map(usuarioEntity.get(), UsuarioEntity.class);
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
    public UsuarioRequestDTO createUsuario(NewUsuarioRequestDTO newUsuarioRequestDTO) {
        Optional<UsuarioEntity> usuario = usuarioJpaRepository.getUsuarioEntitiesByDni(newUsuarioRequestDTO.getDni());
        if (usuario.isPresent()){
            throw new RuntimeException("El usuario ya existe");
        }
        LocalDate fechaAlta = LocalDate.now();
        UsuarioEntity usuarioCreated = new UsuarioEntity();
        usuarioCreated.setDni(newUsuarioRequestDTO.getDni());
        usuarioCreated.setFechaNacimiento(newUsuarioRequestDTO.getFechaNacimiento());
        usuarioCreated.setTelefono(newUsuarioRequestDTO.getTelefono());
        usuarioCreated.setDireccion(newUsuarioRequestDTO.getDireccion());
        usuarioCreated.setEmail(newUsuarioRequestDTO.getEmail());
        usuarioCreated.setFechaAlta(fechaAlta);

        usuarioJpaRepository.save(usuarioCreated);

        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setDni(usuarioCreated.getDni());
        usuarioRequestDTO.setFechaNacimiento(usuarioCreated.getFechaNacimiento());
        usuarioRequestDTO.setTelefono(usuarioCreated.getTelefono());
        usuarioRequestDTO.setDireccion(usuarioCreated.getDireccion());
        usuarioRequestDTO.setEmail(usuarioCreated.getEmail());
        return usuarioRequestDTO;
        //el mapeo no esta con modelMapper porque sino no no pasaba el test
    }


    @Override
    public void deleteUsuario(Long dni) {
        Optional <UsuarioEntity> usuario = usuarioJpaRepository.getUsuarioEntitiesByDni(dni);
        if (usuario.isEmpty()){
            throw new RuntimeException("The User not exist in db");
        }
        usuarioJpaRepository.delete(usuario.get());
    }

    @Override
    public UsuarioRequestDTO updateUsuario(Long dni, UpdateUsuarioRequestDTO newData) {
        Optional <UsuarioEntity> usuarioOptional = usuarioJpaRepository.getUsuarioEntitiesByDni(dni);
        if (usuarioOptional.isEmpty()){
            throw new RuntimeException("User not exist");
        }
        UsuarioEntity usuario = usuarioOptional.get();
        usuario.setPassword(newData.getPassword());
        usuario.setFechaNacimiento(newData.getFechaNacimiento());
        usuario.setTelefono(newData.getTelefono());
        usuario.setDireccion(newData.getDireccion());
        usuario.setEmail(newData.getEmail());
        usuarioJpaRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioRequestDTO.class);
    }


}
