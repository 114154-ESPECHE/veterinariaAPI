package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Usuario.NewUsuarioRequestDTO;
import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Repositories.jpa.UsuarioJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
            when(usuarioJpaRepository.getUsuarioEntitiesByEmail(usuarioEntity.getEmail())).thenReturn(usuarioEntity);

            UsuarioEntity result = usuarioService.getUsuarioByEmail(usuarioEntity.getEmail());

            assertNotNull(result);
            assertEquals("arroba@arroba.com", result.getEmail());
    }

    @Test
    void createUsuario() {
        // Datos del nuevo usuario que no existe en el repositorio
        NewUsuarioRequestDTO newUsuarioRequestDTO = new NewUsuarioRequestDTO();
        newUsuarioRequestDTO.setDni(12345678L);
        newUsuarioRequestDTO.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        newUsuarioRequestDTO.setTelefono(123456L);
        newUsuarioRequestDTO.setDireccion("Calle Falsa 123");
        newUsuarioRequestDTO.setEmail("usuario@ejemplo.com");

        // Configuramos el mock del usuarioJpaRepository para devolver un Optional vacío, indicando que el usuario no existe
        when(usuarioJpaRepository.getUsuarioEntitiesByDni(newUsuarioRequestDTO.getDni())).thenReturn(Optional.empty());

        // Configuramos el mock para devolver el objeto mapeado correctamente
        UsuarioEntity usuarioCreated = new UsuarioEntity();
        usuarioCreated.setDni(newUsuarioRequestDTO.getDni());
        usuarioCreated.setFechaNacimiento(newUsuarioRequestDTO.getFechaNacimiento());
        usuarioCreated.setTelefono(newUsuarioRequestDTO.getTelefono());
        usuarioCreated.setDireccion(newUsuarioRequestDTO.getDireccion());
        usuarioCreated.setEmail(newUsuarioRequestDTO.getEmail());
        when(usuarioJpaRepository.save(any())).thenReturn(usuarioCreated);
        when(modelMapper.map(usuarioCreated, UsuarioRequestDTO.class)).thenReturn(new UsuarioRequestDTO());

        // Ejecutamos el método createUsuario
        UsuarioRequestDTO result = usuarioService.createUsuario(newUsuarioRequestDTO);

        // Verificamos que se llama al método save() del usuarioJpaRepository para guardar el nuevo usuario
        verify(usuarioJpaRepository, times(1)).save(any());

        // Verificamos que se devuelve el objeto mapeado correctamente
        assertEquals(12345678L, result.getDni());
        assertEquals(LocalDate.of(1990, 5, 15), result.getFechaNacimiento());
        assertEquals(123456L, result.getTelefono());
        assertEquals("Calle Falsa 123", result.getDireccion());
        assertEquals("usuario@ejemplo.com", result.getEmail());
    }

    @Test
    void deleteUsuario() {
            when(usuarioJpaRepository.getUsuarioEntitiesByDni(3L)).thenReturn(Optional.empty());

    }

    @Test
    void updateUsuario() {
    }
}