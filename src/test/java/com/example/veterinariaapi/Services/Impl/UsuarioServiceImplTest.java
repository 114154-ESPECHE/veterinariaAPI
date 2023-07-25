package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Repositories.jpa.UsuarioJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {

    @Mock
    ModelMapper modelMapper;
    @Mock
    UsuarioJpaRepository usuarioJpaRepository;

    @InjectMocks
    UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUsuarioByDni() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setDni(35576827L);
        usuarioEntity.setEmail("string@string.com");

        when()
    }

    @Test
    void getUsuarioByEmail() {
    }

    @Test
    void createUsuario() {
    }

    @Test
    void deleteUsuario() {
    }

    @Test
    void updateUsuario() {
    }
}