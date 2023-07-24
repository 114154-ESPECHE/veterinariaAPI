package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Dtos.Cliente.NewClienteRequestDTO;
import com.example.veterinariaapi.Dtos.Cliente.UpdateClienteRequestDTO;
import com.example.veterinariaapi.Models.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    Cliente getClienteById(Long id);

    ClienteResponseDTO getClienteResponseDTOById(Long id);//para el negocio no esta bien este metodo
    //ya que un cliente no sabria su id

    ClienteResponseDTO getClienteByNombreAndTelefono(String nobre, Long telefono);

    ClienteResponseDTO saveCliente(NewClienteRequestDTO newClienteRequestDTO);

    Cliente updateCliente(Long id, Cliente cliente);

    UpdateClienteRequestDTO updateClienteDTO(Long id, Cliente cliente);

    void deleteCliente(Long id);
}
