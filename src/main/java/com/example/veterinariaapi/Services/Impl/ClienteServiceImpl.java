package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Cliente.NewClienteRequestDTO;
import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Dtos.Cliente.UpdateClienteRequestDTO;
import com.example.veterinariaapi.Entities.ClienteEntity;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Repositories.jpa.ClienteJpaRepository;
import com.example.veterinariaapi.Services.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteJpaRepository clienteJpaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cliente getClienteById(Long id) {
        ClienteEntity clienteEntity = clienteJpaRepository.getReferenceById(id);
        if (Objects.isNull(clienteEntity)){
            throw new EntityNotFoundException();
        }
        return modelMapper.map(clienteEntity, Cliente.class);
    }

    @Override
    public ClienteResponseDTO getClienteResponseDTOById(Long id) {
        ClienteEntity clienteEntity = clienteJpaRepository.getReferenceById(id);
        if (Objects.isNull(clienteEntity)){
            throw new EntityNotFoundException();
        }
        ClienteResponseDTO crDTO = modelMapper.map(clienteEntity, ClienteResponseDTO.class);
        return crDTO;
    }

    @Override
    public ClienteResponseDTO getClienteByNombreAndTelefono(String nombre, Long telefono) {
        Optional<ClienteEntity> clienteEntity = clienteJpaRepository.findClienteEntitiesByNombreAndTelefono(nombre, telefono);
        if (!clienteEntity.isPresent()){
            throw new EntityNotFoundException();
        }
        ClienteResponseDTO cliente = modelMapper.map(clienteEntity.get(), ClienteResponseDTO.class);
        return cliente;
    }

    @Override
    public ClienteResponseDTO saveCliente(NewClienteRequestDTO newClienteRequestDTO) {
        Optional<ClienteEntity> clienteOptional= clienteJpaRepository.findClienteEntitiesByNombreAndApellido(newClienteRequestDTO.getNombre(),newClienteRequestDTO.getApellido());
        if (clienteOptional.isPresent()){
            throw new RuntimeException("The Client exist");
        }
        ClienteEntity clienteEntity = modelMapper.map(newClienteRequestDTO, ClienteEntity.class);
        clienteEntity = clienteJpaRepository.save(clienteEntity);

        return modelMapper.map(clienteEntity, ClienteResponseDTO.class);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        ClienteEntity clienteEntity = clienteJpaRepository.getReferenceById(id);
        if (Objects.isNull(clienteEntity.getId())){
            throw new RuntimeException("Cliente no encontrado");
        }
        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setDireccion(cliente.getDireccion());
        clienteEntity.setTelefono(cliente.getTelefono());
        clienteEntity.setEmail(cliente.getEmail());
        clienteJpaRepository.save(clienteEntity);
        return modelMapper.map(cliente,Cliente.class);
    }

    @Override
    public UpdateClienteRequestDTO updateClienteDTO(Long id, Cliente cliente) {
        ClienteEntity clienteEntity = clienteJpaRepository.getReferenceById(id);
        if (Objects.isNull(clienteEntity)){
            throw new RuntimeException("Cliente no encontrado");
        }
        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setDireccion(cliente.getDireccion());
        clienteEntity.setTelefono(cliente.getTelefono());
        clienteEntity.setEmail(cliente.getEmail());
        clienteJpaRepository.save(clienteEntity);
        return modelMapper.map(clienteEntity, UpdateClienteRequestDTO.class);
    }

    //DeleteCliente funciona, hay que elegir primero un cliente que no tenga mascota
    //por regla de sql.
    @Override
    public void deleteCliente(Long id) {
        ClienteEntity cliente = clienteJpaRepository.getReferenceById(id);
        if (Objects.isNull(cliente.getNombre())){
            throw new RuntimeException("Cliente no encontrado");
        }
        clienteJpaRepository.delete(cliente);
    }


}
