package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Dtos.Cliente.MascotaDTO;
import com.example.veterinariaapi.Dtos.Cliente.NewClienteRequestDTO;
import com.example.veterinariaapi.Dtos.Cliente.UpdateClienteRequestDTO;
import com.example.veterinariaapi.Entities.MascotaEntity;
import com.example.veterinariaapi.Models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    //Cliente getClienteById(Long id);
    Cliente getClienteByDni(Long dni);

    ClienteResponseDTO getClienteResponseDTOBDni(Long dni);//para el negocio no esta bien este metodo
    //ya que un cliente no sabria su id

    ClienteResponseDTO getClienteByNombreAndTelefono(String nobre, Long telefono);

    ClienteResponseDTO saveCliente(NewClienteRequestDTO newClienteRequestDTO);

    Cliente updateCliente(Long dni, Cliente cliente);

    UpdateClienteRequestDTO updateClienteDTO(Long id, UpdateClienteRequestDTO updateClienteRequestDTO);

    void deleteCliente(Long id);

    List<MascotaDTO> getMascotasByClienteDni(Long dni);
}
