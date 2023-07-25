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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {

        @Mock
        ModelMapper modelMapper;
        @Mock
        UsuarioJpaRepository usuarioJpaRepository;

        @InjectMocks
        UsuarioServiceImpl usuarioService;

        private UsuarioEntity usuarioEntity;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.initMocks(this);

            usuarioEntity = new UsuarioEntity();
            usuarioEntity.setDni(35576827L);
            usuarioEntity.setPassword("123456");
            usuarioEntity.setTelefono(123456L);
            usuarioEntity.setDireccion("llslssllssl");
            usuarioEntity.setEmail("arroba@arroba.com");
        }

        @Test
        void getUsuarioByDni() {
            when(usuarioJpaRepository.getUsuarioEntitiesByDni(usuarioEntity.getDni())).thenReturn(Optional.of(usuarioEntity));
            when(modelMapper.map(usuarioEntity, UsuarioEntity.class)).thenReturn(usuarioEntity);

            UsuarioEntity result = usuarioService.getUsuarioByDni(usuarioEntity.getDni());

            assertNotNull(result);
            assertEquals(35576827L,result.getDni());
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