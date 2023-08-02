package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Models.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Usuario loginUsuario(Long dni, String password);
    Cliente loginCliente(Long dni, String password);
}
