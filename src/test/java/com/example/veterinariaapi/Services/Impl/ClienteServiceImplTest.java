package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Entities.ClienteEntity;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Repositories.jpa.ClienteJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {

    @Mock
    private ClienteJpaRepository clienteJpaRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getClienteById() {
        Long existingId = 1L;
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(existingId);
        clienteEntity.setNombre("Agustin");
        clienteEntity.setApellido("Espeche");
        clienteEntity.setEmail("lalala@lalala.com");

        Cliente clienteExpected = new Cliente();
        clienteExpected.setId(existingId);
        clienteExpected.setNombre("Agustin");
        clienteExpected.setApellido("Espeche");
        clienteExpected.setEmail("lalala@lalala.com");

        when(clienteJpaRepository.getReferenceById(existingId)).thenReturn(clienteEntity);
        when(modelMapper.map(clienteEntity, Cliente.class)).thenReturn(clienteExpected);

        // Act
        Cliente result = clienteService.getClienteById(existingId);

        // Assert
        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("Agustin", result.getNombre());
        assertEquals("Espeche", result.getApellido());
        assertEquals("lalala@lalala.com", result.getEmail());
    }

    @Test
    void getClienteResponseDTOById() {
        Long existingId = 1L;
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setTelefono(351351315L);
        clienteEntity.setNombre("Agustin");
        clienteEntity.setApellido("Espeche");
        clienteEntity.setEmail("lalala@lalala.com");

        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setTelefono(351351315L);
        clienteResponseDTO.setNombre("Agustin");
        clienteResponseDTO.setApellido("Espeche");
        clienteResponseDTO.setEmail("lalala@lalala.com");

        when(clienteJpaRepository.getReferenceById(existingId)).thenReturn(clienteEntity);
        when(modelMapper.map(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);
    }
}