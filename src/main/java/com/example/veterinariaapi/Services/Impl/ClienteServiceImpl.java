package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Cliente.MascotaDTO;
import com.example.veterinariaapi.Dtos.Cliente.NewClienteRequestDTO;
import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Dtos.Cliente.UpdateClienteRequestDTO;
import com.example.veterinariaapi.Entities.ClienteEntity;
import com.example.veterinariaapi.Entities.MascotaEntity;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Repositories.jpa.ClienteJpaRepository;
import com.example.veterinariaapi.Services.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public Cliente updateCliente(Long id, Cliente cliente) {
        Optional<ClienteEntity> clienteOptional = clienteJpaRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new EntityNotFoundException("Cliente no encontrado con el ID: " + id);
        }

        ClienteEntity clienteEntity = clienteOptional.get();

        if (cliente.getNombre() != null) {
            clienteEntity.setNombre(cliente.getNombre());
        }
        if (cliente.getApellido() != null) {
            clienteEntity.setApellido(cliente.getApellido());
        }
        if (cliente.getDireccion() != null) {
            clienteEntity.setDireccion(cliente.getDireccion());
        }
        if (cliente.getTelefono() != null) {
            clienteEntity.setTelefono(cliente.getTelefono());
        }
        if (cliente.getEmail() != null) {
            clienteEntity.setEmail(cliente.getEmail());
        }

        clienteJpaRepository.save(clienteEntity);

        return modelMapper.map(clienteEntity, Cliente.class);
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
        Optional<ClienteEntity> cliente = clienteJpaRepository.findById(id);
        if (cliente.isEmpty()){
            throw new EntityNotFoundException("Cliente no encontrado");
        }

        clienteJpaRepository.deleteById(cliente.get().getId());
    }

    @Override
    public List<MascotaDTO> getMascotasByClienteDni(Long dni) {
        List<MascotaEntity> mascotas = clienteJpaRepository.findMascotasByClienteDni(dni);

        List<MascotaDTO> mascotasDTO = mascotas.stream()
                .map(mascota -> new MascotaDTO(
                        mascota.getId(),
                        mascota.getNombre(),
                        mascota.getColor(),
                        mascota.getEdad(),
                        mascota.getEspecie()
                ))
                .collect(Collectors.toList());

        return mascotasDTO;
    }


}
