package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Dtos.Usuario.UsuarioRequestDTO;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Models.Usuario;
import com.example.veterinariaapi.Services.ClienteService;
import com.example.veterinariaapi.Services.LoginService;
import com.example.veterinariaapi.Services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Usuario loginUsuario(Long dni, String password) {
        UsuarioRequestDTO usuarioRequestDTO = usuarioService.getUsuarioByDniAndPassword(dni, password);

        if (usuarioRequestDTO == null){
            throw new EntityNotFoundException("User not exist");
        }
        return modelMapper.map(usuarioRequestDTO, Usuario.class);
    }

    @Override
    public Cliente loginCliente(Long dni, String password) {
        ClienteResponseDTO clienteResponseDTO = clienteService.getClienteEntityByDniAndPassword(dni, password);
        if (clienteResponseDTO == null){
            throw new EntityNotFoundException("Client not exist");
        }
        return modelMapper.map(clienteResponseDTO, Cliente.class);

    }
}
