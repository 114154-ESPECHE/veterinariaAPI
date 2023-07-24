package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Dtos.Cliente.NewClienteRequestDTO;
import com.example.veterinariaapi.Entities.ClienteEntity;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Repositories.jpa.ClienteJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteJpaRepository clienteJpaRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteEntity clienteEntity;
    private ClienteResponseDTO clienteResponseDTO;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setNombre("Agustin");
        clienteEntity.setApellido("Espeche");
        clienteEntity.setTelefono(351351315L);
        clienteEntity.setEmail("lalala@lalala.com");


        clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setTelefono(351351315L);
        clienteResponseDTO.setNombre("Agustin");
        clienteResponseDTO.setApellido("Espeche");
        clienteResponseDTO.setEmail("lalala@lalala.com");
    }

    @Test
    void getClienteById() {

        Cliente clienteExpected = new Cliente();
        clienteExpected.setId(1L);
        clienteExpected.setNombre("Agustin");
        clienteExpected.setApellido("Espeche");
        clienteExpected.setEmail("lalala@lalala.com");

        when(clienteJpaRepository.getReferenceById(1L)).thenReturn(clienteEntity);
        when(modelMapper.map(clienteEntity, Cliente.class)).thenReturn(clienteExpected);

        // Act
        Cliente result = clienteService.getClienteById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Agustin", result.getNombre());
        assertEquals("Espeche", result.getApellido());
        assertEquals("lalala@lalala.com", result.getEmail());
    }

    @Test
    void getClienteResponseDTOById() {

        when(clienteJpaRepository.getReferenceById(1L)).thenReturn(clienteEntity);
        when(modelMapper.map(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);
    }

    @Test
    void getClienteByNombreAndTelefono() {

        when(clienteJpaRepository.findClienteEntitiesByNombreAndTelefono("Agustin", 351351315L)).thenReturn(Optional.of(clienteEntity));

        when(modelMapper.map(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO result = clienteService.getClienteByNombreAndTelefono("Agustin", 351351315L);

        assertNotNull(result);
        assertEquals("Agustin",result.getNombre());
        assertEquals(351351315L, result.getTelefono());

    }

    @Test
    void saveCliente() {
        NewClienteRequestDTO newClienteRequestDTO = new NewClienteRequestDTO();
        newClienteRequestDTO.setNombre("AgustinNuevo");
        newClienteRequestDTO.setApellido("EspecheNuevo");

        ClienteResponseDTO clienteResponseDTO1 = new ClienteResponseDTO();
        clienteResponseDTO1.setNombre("AgustinNuevo");
        clienteResponseDTO1.setApellido("EspecheNuevo");


        when(clienteJpaRepository.findClienteEntitiesByNombreAndApellido(newClienteRequestDTO.getNombre(), newClienteRequestDTO.getApellido())).thenReturn(Optional.empty());


        when(modelMapper.map(newClienteRequestDTO, ClienteEntity.class)).thenReturn(clienteEntity);
        when(clienteJpaRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(modelMapper.map(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO1);


        ClienteResponseDTO result = clienteService.saveCliente(newClienteRequestDTO);

        assertNotNull(result);
        assertEquals("AgustinNuevo", result.getNombre());
        assertEquals("EspecheNuevo", result.getApellido());
    }

    @Test
    void updateCliente() {
        Long existingId = 1L;

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(existingId);
        clienteEntity.setNombre("NombreAnterior");
        clienteEntity.setApellido("ApellidoAnterior");
        clienteEntity.setDireccion("direccio anterior");
        clienteEntity.setTelefono(3512153L);
        clienteEntity.setEmail("email@email.com");

        Cliente clienteToUpdate = new Cliente();
        clienteToUpdate.setId(existingId);
        clienteToUpdate.setNombre("NuevoNombre");
        clienteToUpdate.setApellido("NuevoApellido");
        clienteToUpdate.setDireccion("direccio nueva");
        clienteToUpdate.setTelefono(1111111L);
        clienteToUpdate.setEmail("email@emailNuevo.com");

        // Configurar el mock del clienteJpaRepository para devolver el clienteEntity existente
        when(clienteJpaRepository.getReferenceById(existingId)).thenReturn(clienteEntity);

        // Configurar el mock del clienteJpaRepository para devolver el mismo clienteEntity después de guardar
        when(clienteJpaRepository.save(any(ClienteEntity.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // Act
        Cliente result = clienteService.updateCliente(existingId, clienteToUpdate);


        // Assert
        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("NuevoNombre", result.getNombre());
        assertEquals("NuevoApellido", result.getApellido());

        // Verificar que el método clienteJpaRepository.save se llamó una vez
        verify(clienteJpaRepository, times(1)).save(clienteEntity);
    }

    @Test
    void updateClienteDTO() {


    }

    @Test
    void deleteCliente() {
    }
}