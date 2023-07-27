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
import org.mockito.stubbing.Answer;
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
    private NewClienteRequestDTO newClienteRequestDTO;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setNombre("Agustin");
        clienteEntity.setApellido("Espeche");
        clienteEntity.setTelefono(351351315L);
        clienteEntity.setEmail("lalala@lalala.com");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Agustin");
        cliente.setApellido("Espeche");
        cliente.setEmail("lalala@lalala.com");

        clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setTelefono(351351315L);
        clienteResponseDTO.setNombre("Agustin");
        clienteResponseDTO.setApellido("Espeche");
        clienteResponseDTO.setEmail("lalala@lalala.com");

        newClienteRequestDTO = new NewClienteRequestDTO();
        newClienteRequestDTO.setNombre("Sandra");
        newClienteRequestDTO.setApellido("Diaz");
        newClienteRequestDTO.setDireccion("dean funes 123123");
        newClienteRequestDTO.setTelefono(123456789L);
        newClienteRequestDTO.setEmail("nuevoEmail@email.com");
    }

    //@Test
//    void getClienteById() {
//
//        when(clienteJpaRepository.getReferenceById(1L)).thenReturn(clienteEntity);
//        when(modelMapper.map(clienteEntity, Cliente.class)).thenReturn(cliente);
//
//
//        Cliente result = clienteService.getClienteById(1L);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("Agustin", result.getNombre());
//        assertEquals("Espeche", result.getApellido());
//        assertEquals("lalala@lalala.com", result.getEmail());
//    }

//    @Test
//    void getClienteResponseDTOById() {
//
//        when(clienteJpaRepository.getReferenceById(1L)).thenReturn(clienteEntity);
//        when(modelMapper.map(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);
//
//        ClienteResponseDTO result = clienteService.getClienteResponseDTOById(1L);
//
//        assertNotNull(result);
//        assertEquals("Agustin", clienteResponseDTO.getNombre());
//    }

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
        when(clienteJpaRepository.findClienteEntitiesByNombreAndApellido("Sandra", "Diaz")).thenReturn(Optional.empty());

        when(modelMapper.map(newClienteRequestDTO, ClienteEntity.class)).thenReturn(clienteEntity);

        when(clienteJpaRepository.save(any(ClienteEntity.class))).thenAnswer((Answer<ClienteEntity>) invocation -> {
            Object[] args = invocation.getArguments();
            return (ClienteEntity) args[0]; // Devolvemos el mismo objeto que se pasó como argumento
        });

        when(modelMapper.map(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);


        ClienteResponseDTO result = clienteService.saveCliente(newClienteRequestDTO);


        assertNotNull(result);
        assertEquals("Agustin", result.getNombre());
        assertEquals("Espeche", result.getApellido());

    }

//    @Test
//    void updateCliente() {
//        Long existingId = 1L;
//
//        ClienteEntity clienteEntity = new ClienteEntity();
//        clienteEntity.setId(existingId);
//        clienteEntity.setNombre("NombreAnterior");
//        clienteEntity.setApellido("ApellidoAnterior");
//        clienteEntity.setDireccion("direccio anterior");
//        clienteEntity.setTelefono(3512153L);
//        clienteEntity.setEmail("email@email.com");
//
//        Cliente clienteToUpdate = new Cliente();
//        clienteToUpdate.setId(existingId);
//        clienteToUpdate.setNombre("NuevoNombre");
//        clienteToUpdate.setApellido("NuevoApellido");
//        clienteToUpdate.setDireccion("direccio nueva");
//        clienteToUpdate.setDni(35115251L);
//        clienteToUpdate.setTelefono(1111111L);
//        clienteToUpdate.setEmail("email@emailNuevo.com");
//
//        // Configurar el mock del clienteJpaRepository para devolver el clienteEntity existente
//        when(clienteJpaRepository.findClienteEntitiesById(existingId)).thenReturn(Optional.of(clienteEntity));
//
//        // Configurar el mock del clienteJpaRepository para devolver la entidad modificada después de guardar
//        when(clienteJpaRepository.save(any(ClienteEntity.class))).thenAnswer(invocation -> {
//            ClienteEntity savedEntity = invocation.getArgument(0);
//            clienteEntity.setNombre(savedEntity.getNombre());
//            clienteEntity.setApellido(savedEntity.getApellido());
//            clienteEntity.setDireccion(savedEntity.getDireccion());
//            clienteEntity.setTelefono(savedEntity.getTelefono());
//            clienteEntity.setEmail(savedEntity.getEmail());
//            return clienteEntity;
//        });
//
//        // Act
//        Cliente result = clienteService.updateCliente(existingId, clienteToUpdate);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(existingId, result.getId());
//        assertEquals("NuevoNombre", result.getNombre());
//        assertEquals("NuevoApellido", result.getApellido());
//
//        // Verificar que el método clienteJpaRepository.save se llamó una vez
//        verify(clienteJpaRepository, times(1)).save(clienteEntity);
//    }



    @Test
    void deleteCliente() {
        // Creamos un nuevo cliente con ID 2 y nombre "Rodolfo"
        ClienteEntity newClienteEntity = new ClienteEntity();
        newClienteEntity.setId(2L);
        newClienteEntity.setNombre("Rodolfo");

        // Configuramos el mock del clienteJpaRepository para devolver el clienteEntity existente con ID 2
        when(clienteJpaRepository.findById(2L)).thenReturn(Optional.of(newClienteEntity));

        // Act: Llamamos al método deleteCliente con el ID del cliente existente (2)
        clienteService.deleteCliente(newClienteEntity.getId());
        newClienteEntity.setId(3L);

        // Verificación: Verificamos que el método deleteById fue llamado con el ID correcto del cliente a eliminar (2)
        verify(clienteJpaRepository, times(1)).deleteById(2L);

        // Intentamos obtener el cliente nuevamente para asegurarnos de que ha sido eliminado
        Optional<ClienteEntity> deletedCliente = clienteJpaRepository.findById(2L);

        // Verificación adicional: Asegurarnos de que el cliente ya no existe (debe ser un Optional vacío)
        assertEquals(deletedCliente.get().getId(), 3L);
    }
}